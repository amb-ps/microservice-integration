package com.example.demo.controller;

import com.example.demo.dto.ProductResponse;
import com.example.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<String> findAllProducts() {
        return productService.getProuctListFromMicro();
    }

    @GetMapping("/integrated/products")
    public ProductResponse getProducts() {
        boolean microServiceIntegrated = true;
        List<String> productsList = findAllProducts();

        return new ProductResponse(microServiceIntegrated, productsList);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Healthy");
    }
}
