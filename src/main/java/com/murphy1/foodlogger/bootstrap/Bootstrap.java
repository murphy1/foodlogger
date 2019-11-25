package com.murphy1.foodlogger.bootstrap;

import com.murphy1.foodlogger.model.Category;
import com.murphy1.foodlogger.model.Product;
import com.murphy1.foodlogger.repositories.CategoryRepository;
import com.murphy1.foodlogger.repositories.ProductRepository;
import lombok.Builder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Builder
public class Bootstrap implements CommandLineRunner {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public Bootstrap(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.deleteAll().block();
        categoryRepository.deleteAll().block();

        productRepository.save(Product.builder().name("products").calories(250.0).description("A Great Product").build()).block();

        categoryRepository.save(Category.builder().name("Category1").description("Spicy").build()).block();
    }
}
