package com.tryton.price_calculator.service.discount;

import java.math.BigDecimal;

public interface DiscountPolicy {
     BigDecimal applyDiscount(CalculationContext calculationContext);
}
