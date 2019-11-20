package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.model.Product;
import com.murphy1.foodlogger.repositories.ProductRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Builder
@RestController
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/api/logger/products")
    Flux<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/api/logger/products/{id}")
    Mono<Product> getProductById(@PathVariable String id){
        return productRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/logger/products")
    Mono<Void> createProduct(@RequestBody Publisher<Product> productPublisher){
        return productRepository.saveAll(productPublisher).then();
    }

    @PutMapping("/api/logger/products/{id}")
    Mono<Product> updateProduct(@PathVariable String id, @RequestBody Product product){
        product.setId(id);
        return productRepository.save(product);
    }

    @DeleteMapping("/api/logger/products/{id}")
    Mono<Void> deleteProductById(@PathVariable String id){
        return productRepository.deleteById(id);
    }

}
