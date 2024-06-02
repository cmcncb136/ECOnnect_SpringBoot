package com.econnect.econnect.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all/")
    public List<ProductDto> getAll() {
        ArrayList<Product> soruce = (ArrayList) productService.findAll();
        ArrayList<ProductDto> products = new ArrayList<>();

        for(Product p : soruce)
            products.add(ProductDto.toDto(p));

        return products;
    }

    @GetMapping("/find/")
    public ProductDto find(@RequestParam("id") String id) {
        return ProductDto.toDto(productService.findById(id));
    }

    @GetMapping("/findCategory/")
    public List<ProductDto> findCategory(@RequestParam("categoryId") String categoryId) {

        return null;
    }
}
