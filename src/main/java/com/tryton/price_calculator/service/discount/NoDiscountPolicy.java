package com.tryton.price_calculator.service.discount;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.tryton.price_calculator.service.discount.DiscountPolicyConstants.DEFAULT_SCALE;

@Component
public class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public BigDecimal applyDiscount(CalculationContext calculationContext) {
        BigDecimal price = calculationContext.getPrice();
        return price.multiply(BigDecimal.valueOf(calculationContext.getQuantity()))
                .setScale(DEFAULT_SCALE, RoundingMode.CEILING);
    }
}
