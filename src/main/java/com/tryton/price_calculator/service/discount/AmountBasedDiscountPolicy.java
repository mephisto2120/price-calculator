package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.model.AmountDiscountPolicyConfig;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class AmountBasedDiscountPolicy implements DiscountPolicy {
    private final AmountDiscountPolicyConfig amountDiscountPolicyConfig;
    @Override
    public BigDecimal applyDiscount(CalculationContext calculationContext) {
        BigDecimal discount = amountDiscountPolicyConfig.getDiscountMap()
                .ceilingEntry(calculationContext.getQuantity())
                .getValue()
                .divide(BigDecimal.valueOf(100));;
        BigDecimal multiplicand = BigDecimal.ONE.subtract(discount);
        return calculationContext.getPrice()
                .multiply(multiplicand);
    }
}
