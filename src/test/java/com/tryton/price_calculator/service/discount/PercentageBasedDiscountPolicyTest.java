package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.model.PercentageDiscountPolicyConfig;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.tryton.price_calculator.service.discount.DiscountPolicyConstants.DEFAULT_SCALE;
import static org.assertj.core.api.Assertions.assertThat;

class PercentageBasedDiscountPolicyTest {
    private static final BigDecimal INITIAL_PRICE = BigDecimal.TEN;
    private static final int QUANTITY_1 = 5;
    private static final int QUANTITY_2 = 20;
    private static final BigDecimal DISCOUNT_IN_PERCENTS = BigDecimal.TEN;

    private PercentageBasedDiscountPolicy percentageBasedDiscountPolicy;

    @ParameterizedTest
    @MethodSource
    void shouldApplyDiscountBasedOnPercentage(int quantity, BigDecimal expected) {
        //given
        CalculationContext calculationContext = DefaultCalculationContext.builder()
                .price(INITIAL_PRICE)
                .quantity(quantity)
                .build();

        PercentageDiscountPolicyConfig percentageDiscountPolicyConfig = PercentageDiscountPolicyConfig.builder()
                .discount(DISCOUNT_IN_PERCENTS)
                .build();
        percentageBasedDiscountPolicy = new PercentageBasedDiscountPolicy(percentageDiscountPolicyConfig);

        //when
        BigDecimal price = percentageBasedDiscountPolicy.applyDiscount(calculationContext);

        //then
        assertThat(price).isEqualTo(expected);
    }

    private static Stream<Arguments> shouldApplyDiscountBasedOnPercentage() {
        return Stream.of(
                Arguments.of(QUANTITY_1, BigDecimal.valueOf(45).setScale(DEFAULT_SCALE)),
                Arguments.of(QUANTITY_2, BigDecimal.valueOf(180).setScale(DEFAULT_SCALE)),
                Arguments.of(0, BigDecimal.ZERO.setScale(DEFAULT_SCALE))
        );
    }
}
