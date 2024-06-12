package com.econnect.econnect.order;

import com.econnect.econnect.cart.CartDto;
import com.econnect.econnect.cart.CartRepository;
import com.econnect.econnect.member.Member;
import com.econnect.econnect.member.MemberRepository;
import com.econnect.econnect.result.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderTotalRepository orderTotalRepository;
    private final CartRepository cartRepository;

    public CommonResult order(List<CartDto> carts, String uid, int usePoint){
        int totalPoint = 0;
        Member member = memberRepository.findById(uid).orElse(null);
        if(member == null) return CommonResult.builder()
                .msg("존재하지 않는 맴버입니다.")
                .code(-1)
                .build();

        if(member.getPoint() < usePoint) return CommonResult.builder()
                .msg("포인트가 부족합니다.")
                .code(-2)
                .build();

        if(carts == null || carts.isEmpty()) return CommonResult.builder()
                .msg("상품목록이 없습니다.")
                .code(-3)
                .build();

        OrderTotal orderTotal = new OrderTotal().builder()
                .member(member)
                .orderDate(LocalDate.now())
                .totalCount(0)
                .totalPrice(0)
                .build();

        orderTotal = orderTotalRepository.save(orderTotal); //orderTotal 만들고 id가져오기
        List<OrderDetail> orderDetails = new ArrayList<>();
        for(CartDto cart : carts){
            HistoryId historyId = new HistoryId().builder() //복합키 만들기
                    .orderTotalId(orderTotal.getTotalOrderId())
                    .productId(cart.getProduct().getProductId())
                    .build();

            OrderDetail orderDetail = new OrderDetail().builder() //주문정보 만들기
                    .historyId(historyId)
                    .orderTotal(orderTotal)
                    .count(cart.getCount())
                    .totalPrice(cart.getCount() * cart.getProduct().getPrice())
                    .build();

            System.out.println(historyId.toString());
            orderDetailRepository.save(orderDetail);
            orderTotal.getDetailOrderList().add(orderDetail);

            totalPoint += cart.getCount() * cart.getProduct().getFeedbackPoint();
            orderTotal.setTotalPrice(cart.getCount() * cart.getProduct().getPrice() + orderTotal.getTotalPrice());
            orderTotal.setTotalCount(cart.getCount() + orderDetail.getCount());
        }

        orderTotal.setTotalPrice(orderTotal.getTotalPrice() - usePoint);

        orderTotalRepository.save(orderTotal);
        member.setPoint(member.getPoint() - usePoint + totalPoint);
        memberRepository.save(member);

        for(CartDto cart : carts) //구매에 성공하면 카트에서 제거
            cartRepository.deleteById(cart.getId());


        return CommonResult.builder()
                .success(true)
                .msg("구매에 성공했습니다.")
                .code(0)
                .build();
    }
}
