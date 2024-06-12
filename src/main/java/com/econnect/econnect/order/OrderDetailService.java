package com.econnect.econnect.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private OrderDetailRepository orderDetailRepository;

    public OrderDetail getOrderDetail(HistoryId historyId) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(historyId);
        return orderDetail.isPresent() ? orderDetail.get() : null;
    }
    
}
