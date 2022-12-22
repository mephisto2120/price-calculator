package com.tryton.price_calculator.service.discount;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public BigDecimal applyDiscount(CalculationContext calculationContext) {
        return calculationContext.getPrice();
    }
}
