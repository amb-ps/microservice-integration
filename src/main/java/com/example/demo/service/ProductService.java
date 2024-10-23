package com.example.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
@Service
public class ProductService {

    private final WebClient webClient;

    @Autowired
    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<String> getProuctListFromMicro(){
        String productApiUrl = "http://eca.aws.service.demo.eca.aws.service.demo:8080/products";

        return webClient.get()
                .uri(productApiUrl)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> {
                    String productsString = jsonNode.get(0).asText();
                    try {
                        return new ObjectMapper().readValue(productsString, new TypeReference<List<String>>() {});
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Failed to retrieve the list");
                    }
                })
                .block();
    }
}
