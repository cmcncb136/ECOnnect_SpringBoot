package com.econnect.econnect.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.isPresent() ? product.get() : null;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
