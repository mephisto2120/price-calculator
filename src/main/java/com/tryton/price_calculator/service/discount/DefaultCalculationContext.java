package com.tryton.price_calculator.service.discount;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@Value
public class DefaultCalculationContext implements CalculationContext {
    private final BigDecimal price;
    private final int quantity;

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }
}
