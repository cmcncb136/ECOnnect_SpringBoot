package com.econnect.econnect.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRecommendRepository extends JpaRepository<ProductRecommend, Integer> {
    boolean existsByProductAndDate(Product product, LocalDate date);
    List<ProductRecommend> findByDate(LocalDate date);
}
