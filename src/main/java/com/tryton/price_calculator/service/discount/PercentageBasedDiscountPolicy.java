package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.model.PercentageDiscountPolicyConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.tryton.price_calculator.service.discount.DiscountPolicyConstants.DEFAULT_SCALE;

@RequiredArgsConstructor
@CommonsLog
public class PercentageBasedDiscountPolicy implements DiscountPolicy {
    private final PercentageDiscountPolicyConfig percentageDiscountPolicyConfig;

    @Override
    public BigDecimal applyDiscount(CalculationContext calculationContext) {
        BigDecimal discount = percentageDiscountPolicyConfig.getDiscount().divide(BigDecimal.valueOf(100));
        BigDecimal multiplicand = BigDecimal.ONE.subtract(discount);
        BigDecimal priceWithDiscount = calculationContext.getPrice()
                .multiply(multiplicand);
        log.info("priceWithDiscount=" + priceWithDiscount);
        return priceWithDiscount.multiply(BigDecimal.valueOf(calculationContext.getQuantity()))
                .setScale(DEFAULT_SCALE, RoundingMode.CEILING);
    }
}
