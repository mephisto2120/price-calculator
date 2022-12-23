package com.tryton.price_calculator.controller;

import com.tryton.price_calculator.service.PriceCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PriceCalculatorControllerTest {

    @Mock
    private PriceCalculatorService priceCalculatorServiceMock;

    @InjectMocks
    private PriceCalculatorController priceCalculatorController;

    @Test
    void calculate() {
        //given
        String productId = "10.00";
        Integer amount = 10;

        //when
        priceCalculatorController.calculate(productId, amount);

        //then
        then(priceCalculatorServiceMock).should().calculate(productId, amount);
    }
}
