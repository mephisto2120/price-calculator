package com.tryton.price_calculator.service.discount;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NoDiscountPolicyTest {
    private static final BigDecimal INITIAL_PRICE = BigDecimal.TEN;
    private static final int QUANTITY = 1000_000;

    private final NoDiscountPolicy noDiscountPolicy = new NoDiscountPolicy();

    @Test
    void shouldApplyNoDiscount() {
        //given
        CalculationContext calculationContext = DefaultCalculationContext.builder()
                .price(INITIAL_PRICE)
                .quantity(QUANTITY)
                .build();

        //when
        BigDecimal price = noDiscountPolicy.applyDiscount(calculationContext);

        //then
        assertThat(price).isEqualTo(INITIAL_PRICE);
    }
}