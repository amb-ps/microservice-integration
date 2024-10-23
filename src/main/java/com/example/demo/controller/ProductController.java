package com.example.demo.controller;

import com.example.demo.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
public class ProductController {

    private final WebClient webClient;

    @Autowired
    public ProductController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/products")
    public List<String> findAllProducts() {
        String productApiUrl = "http://eca.aws.service.demo.eca.aws.service.demo:8080/products";

        return webClient.get()
                .uri(productApiUrl)
                .retrieve()
                .bodyToFlux(String.class)
                .collectList()
                .block();
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