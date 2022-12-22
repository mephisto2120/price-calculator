package com.tryton.price_calculator.service.discount;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public BigDecimal applyDiscount(CalculationContext calculationContext) {
        BigDecimal price = calculationContext.getPrice();
        return price.multiply(BigDecimal.valueOf(calculationContext.getQuantity()))
                .setScale(2, RoundingMode.CEILING);
    }
}
