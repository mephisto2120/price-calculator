package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.model.PercentageDiscountPolicyConfig;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class PercentageBasedDiscountPolicy implements DiscountPolicy {
    private final PercentageDiscountPolicyConfig percentageDiscountPolicyConfig;

    @Override
    public BigDecimal applyDiscount(CalculationContext calculationContext) {
        BigDecimal discount = percentageDiscountPolicyConfig.getDiscount().divide(BigDecimal.valueOf(100));
        BigDecimal multiplicand = BigDecimal.ONE.subtract(discount);
        return calculationContext.getPrice()
                .multiply(multiplicand);
    }
}
