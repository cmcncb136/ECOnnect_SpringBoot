package com.econnect.econnect.cart;

import com.econnect.econnect.member.Member;
import com.econnect.econnect.member.MemberRepository;
import com.econnect.econnect.product.Product;
import com.econnect.econnect.product.ProductRepository;
import com.econnect.econnect.result.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    private final  CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public Cart find(Integer id) {
        return cartRepository.findById(id).orElse(null);
    }

    public List<Cart> findByMember(Member member) {
        return cartRepository.findAllByMember(member);
    }

    public List<Cart> findByMemberId(String memberId) {
        return this.findByMember(
                memberRepository.findById(memberId).orElse(null)
        );
    }

    public List<CartDto> cartToCartDto(List<Cart> carts) {
        List<CartDto> cartDtos = new ArrayList<>();
        for (Cart cart : carts)
            cartDtos.add(CartDto.toDto(cart));

        return cartDtos;
    }


    public void save(){
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public CommonResult add(String uid, String productId){
        CommonResult rst = CommonResult.builder()
                .code(-1)
                .msg("")
                .success(false)
                .build();

        Member member = memberRepository.findById(uid).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        if(product == null || member == null){
            rst.setMsg("유효하지 않은 Member 또는 상품입니다.");
            return rst;
        }


        if(cartRepository.existsByMemberAndProduct(member, product)){
            rst.setCode(-2);
            rst.setMsg("이미 장바구니에 담겨있습니다.");
            return rst;
        }

        save(Cart.builder()
                .member(member)
                .product(product)
                .count(1)
                .build());

        rst.setCode(0);
        rst.setSuccess(true);
        rst.setMsg("상품이 정상적으로 장바구니에 담겼습니다.");
        return rst;
    }

    public void save(CartDto dto){
        if(dto == null) return;
        Member member = memberRepository.findById(dto.getMemberId()).orElse(null);
        if(member == null) return;
        Product product = productRepository.findById(dto.getProduct().getProductId()).orElse(null);
        if(product == null) return;

        cartRepository.save(Cart.toCart(dto, member, product));
    }

    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }

    public void delete(Integer id){
        cartRepository.deleteById(id);
    }

    public void plusCount(Integer id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) return;
        cart.setCount(cart.getCount() + 1);
        cartRepository.save(cart);
    }

    public void minusCount(Integer id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) return;
        if(cart.getCount() > 1)
            cart.setCount(cart.getCount() - 1);
        cartRepository.save(cart);
    }

    public Boolean selected(Integer id, Boolean selected){
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) return null;
        cart.setSelected(selected);
        cartRepository.save(cart);
        
        return cart.getSelected();
    }

}
