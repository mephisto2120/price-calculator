package com.tryton.price_calculator.service.discount;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.tryton.price_calculator.service.discount.DiscountPolicyConstants.DEFAULT_SCALE;
import static org.assertj.core.api.Assertions.assertThat;

class NoDiscountPolicyTest {
    private static final BigDecimal INITIAL_PRICE = BigDecimal.TEN;
    private static final int QUANTITY_1 = 5;
    private static final int QUANTITY_2 = 20;
    private final NoDiscountPolicy noDiscountPolicy = new NoDiscountPolicy();

    @ParameterizedTest
    @MethodSource
    void shouldApplyNoDiscount(int quantity, BigDecimal expected) {
        //given
        CalculationContext calculationContext = DefaultCalculationContext.builder()
                .price(INITIAL_PRICE)
                .quantity(quantity)
                .build();

        //when
        BigDecimal price = noDiscountPolicy.applyDiscount(calculationContext);

        //then
        assertThat(price).isEqualTo(expected);
    }

    private static Stream<Arguments> shouldApplyNoDiscount() {
        return Stream.of(
                Arguments.of(QUANTITY_1, BigDecimal.valueOf(50).setScale(DEFAULT_SCALE)),
                Arguments.of(QUANTITY_2, BigDecimal.valueOf(200).setScale(DEFAULT_SCALE)),
                Arguments.of(0, BigDecimal.ZERO.setScale(DEFAULT_SCALE))
        );
    }
}