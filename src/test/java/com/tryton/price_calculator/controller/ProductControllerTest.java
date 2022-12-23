package com.tryton.price_calculator.controller;

import com.tryton.price_calculator.model.Product;
import com.tryton.price_calculator.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private static final String UUID = "abc-123";

    @Mock
    private ProductService productServiceMock;
    @InjectMocks
    private ProductController productController;

    @Test
    void getProduct() {
        //given-when
        productController.getProduct(UUID);

        //then
        then(productServiceMock).should().getProduct(UUID);
    }

    @Test
    void addProduct() {
        //given
        Product product = Product.builder()
                .build();

        //when
        productController.addProduct(product);

        //then
        then(productServiceMock).should().addProduct(product);
    }
}
