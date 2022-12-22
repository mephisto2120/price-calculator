package com.tryton.price_calculator.service.discount;

import java.math.BigDecimal;

public interface CalculationContext {
    BigDecimal getPrice();
    int getQuantity();
}
