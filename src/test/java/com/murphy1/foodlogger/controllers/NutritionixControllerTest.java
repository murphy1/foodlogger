package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.services.NutritionixService;
import com.murphy1.foodlogger.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NutritionixControllerTest {

    @Mock
    private NutritionixService nutritionixService;

    @Mock
    private UserService userService;

    private WebTestClient webTestClient;

    private NutritionixController nutritionixController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        nutritionixController = new NutritionixController(nutritionixService, userService);
        webTestClient = WebTestClient.bindToController(nutritionixController).build();
    }

    @Test
    void queryNutritionixApi() throws Exception{
        webTestClient.get()
                .uri("/logger/product_query/some_food")
                .exchange()
                .expectStatus()
                .isOk();
    }
}