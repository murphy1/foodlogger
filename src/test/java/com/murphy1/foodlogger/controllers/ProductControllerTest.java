package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.model.Product;
import com.murphy1.foodlogger.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

class ProductControllerTest {

    private ProductController productController;
    private ProductRepository productRepository;
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productController = new ProductController(productRepository);
        webTestClient = WebTestClient.bindToController(productController).build();
    }

    @Test
    void getAllProducts() throws Exception{
        BDDMockito.given(productRepository.findAll())
                .willReturn(Flux.just(Product.builder().name("product1").build(),
                        Product.builder().name("product2").build()));

        webTestClient.get()
                .uri("/api/logger/products/")
                .exchange()
                .expectBodyList(Product.class)
                .hasSize(2);
    }

    @Test
    void createProductTest() throws Exception{
        BDDMockito.given(productRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Product.builder().build()));

        Mono<Product> productMono = Mono.just(Product.builder().name("New Product").build());

        webTestClient.post()
                .uri("/api/logger/products")
                .body(productMono, Product.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void getProductById() throws Exception{
        BDDMockito.given(productRepository.findById("someId"))
                .willReturn(Mono.just(Product.builder().name("product").build()));

        webTestClient.get()
                .uri("/api/logger/products/someId")
                .exchange()
                .expectBody(Product.class);
    }

    @Test
    void updateProduct() throws Exception{
        BDDMockito.given(productRepository.save(any()))
                .willReturn(Mono.just(Product.builder().build()));

        Mono<Product> productMono = Mono.just(Product.builder().name("product").build());

        webTestClient.put()
                .uri("/api/logger/products/someId")
                .body(productMono, Product.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void deleteProductById() throws Exception{
        BDDMockito.given(productRepository.delete(any()))
                .willReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/logger/products/someId")
                .exchange()
                .expectStatus()
                .isOk();
    }
}