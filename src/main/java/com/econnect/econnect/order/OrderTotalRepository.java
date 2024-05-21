package com.econnect.econnect.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTotalRepository extends JpaRepository<OrderTotal, Integer> {
}
