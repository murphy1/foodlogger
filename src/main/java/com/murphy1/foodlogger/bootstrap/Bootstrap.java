package com.murphy1.foodlogger.bootstrap;

import com.murphy1.foodlogger.model.Product;
import com.murphy1.foodlogger.repositories.ProductRepository;
import lombok.Builder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Builder
public class Bootstrap implements CommandLineRunner {

    private ProductRepository productRepository;

    public Bootstrap(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        productRepository.save(Product.builder().name("products").calories(250.0).description("A Great Product").build()).block();
    }
}
