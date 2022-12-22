package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.model.AmountDiscountPolicyConfig;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@RequiredArgsConstructor
public class AmountBasedDiscountPolicy implements DiscountPolicy {
    private final AmountDiscountPolicyConfig amountDiscountPolicyConfig;
    @Override
    public BigDecimal applyDiscount(CalculationContext calculationContext) {
        Map.Entry<Integer, BigDecimal> floorEntry = amountDiscountPolicyConfig.getDiscountMap()
                .floorEntry(calculationContext.getQuantity());
        if (floorEntry == null) {
            return calculationContext.getPrice();
        }

        BigDecimal discount = floorEntry
                .getValue()
                .divide(BigDecimal.valueOf(100));;
        BigDecimal multiplicand = BigDecimal.ONE.subtract(discount);
        return calculationContext.getPrice()
                .multiply(multiplicand);
    }
}
