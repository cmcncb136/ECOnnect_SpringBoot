package com.econnect.econnect.cart;


import com.econnect.econnect.member.Member;
import com.econnect.econnect.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByMember(Member member);
    List<Cart> findAllByProduct(Product product);
    Boolean existsByMemberAndProduct(Member member, Product product);
}
