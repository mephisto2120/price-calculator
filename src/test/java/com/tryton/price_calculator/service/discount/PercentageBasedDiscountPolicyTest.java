package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.model.PercentageDiscountPolicyConfig;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PercentageBasedDiscountPolicyTest {
    private static final BigDecimal INITIAL_PRICE = BigDecimal.TEN;
    private static final BigDecimal EXPECTED_PRICE = BigDecimal.valueOf(9.00);
    private static final int QUANTITY = 1000_000;
    private static final BigDecimal DISCOUNT_IN_PERCENTS = BigDecimal.TEN;

    private PercentageBasedDiscountPolicy percentageBasedDiscountPolicy;

    @Test
    void shouldApplyDiscountBasedOnPercentage() {
        //given
        CalculationContext calculationContext = DefaultCalculationContext.builder()
                .price(INITIAL_PRICE)
                .quantity(QUANTITY)
                .build();

        PercentageDiscountPolicyConfig percentageDiscountPolicyConfig = PercentageDiscountPolicyConfig.builder()
                .discount(DISCOUNT_IN_PERCENTS)
                .build();
        percentageBasedDiscountPolicy = new PercentageBasedDiscountPolicy(percentageDiscountPolicyConfig);

        //when
        BigDecimal price = percentageBasedDiscountPolicy.applyDiscount(calculationContext);

        //then
        assertThat(price).isEqualTo(EXPECTED_PRICE);
    }
}
