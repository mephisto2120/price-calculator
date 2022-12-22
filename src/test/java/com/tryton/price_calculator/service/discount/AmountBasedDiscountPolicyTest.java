package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.model.AmountDiscountPolicyConfig;
import com.tryton.price_calculator.model.PercentageDiscountPolicyConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AmountBasedDiscountPolicyTest {
    private static final BigDecimal INITIAL_PRICE = BigDecimal.TEN;
    private static final BigDecimal EXPECTED_PRICE_1 = BigDecimal.valueOf(9.00);
    private static final BigDecimal EXPECTED_PRICE_2 = BigDecimal.valueOf(8.00);
    private static final int QUANTITY_1 = 5;
    private static final int QUANTITY_2 = 20;
    private static final BigDecimal DISCOUNT_1_IN_PERCENTS = BigDecimal.TEN;
    private static final BigDecimal DISCOUNT_2_IN_PERCENTS = BigDecimal.valueOf(20);

    private AmountBasedDiscountPolicy amountBasedDiscountPolicy;

    @ParameterizedTest
    @MethodSource
    void applyDiscount(int quantity, BigDecimal expected) {
        //given
        CalculationContext calculationContext = DefaultCalculationContext.builder()
                .price(INITIAL_PRICE)
                .quantity(quantity)
                .build();
        TreeMap<Integer, BigDecimal> discountMap = new TreeMap<>();
        discountMap.put(QUANTITY_1, DISCOUNT_1_IN_PERCENTS);
        discountMap.put(QUANTITY_2, DISCOUNT_2_IN_PERCENTS);

        AmountDiscountPolicyConfig percentageDiscountPolicyConfig = AmountDiscountPolicyConfig.builder()
                .discountMap(discountMap)
                .build();
        amountBasedDiscountPolicy = new AmountBasedDiscountPolicy(percentageDiscountPolicyConfig);

        //when
        BigDecimal price = amountBasedDiscountPolicy.applyDiscount(calculationContext);

        //then
        assertThat(price).isEqualTo(expected);
    }

    private static Stream<Arguments> applyDiscount() {
        return Stream.of(
                Arguments.of(QUANTITY_1, EXPECTED_PRICE_1),
                Arguments.of(QUANTITY_2, EXPECTED_PRICE_2),
                Arguments.of(6, EXPECTED_PRICE_1),
                Arguments.of(21, EXPECTED_PRICE_2),
                Arguments.of(1, INITIAL_PRICE)
        );
    }
}
