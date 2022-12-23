package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.model.AmountDiscountPolicyConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static com.tryton.price_calculator.service.discount.DiscountPolicyConstants.DEFAULT_SCALE;

@RequiredArgsConstructor
@CommonsLog
public class AmountBasedDiscountPolicy implements DiscountPolicy {
    private final AmountDiscountPolicyConfig amountDiscountPolicyConfig;

    @Override
    public BigDecimal applyDiscount(CalculationContext calculationContext) {
        Map.Entry<Integer, BigDecimal> floorEntry = amountDiscountPolicyConfig.getDiscountMap()
                .floorEntry(calculationContext.getQuantity());
        if (floorEntry == null) {
            return multiple(calculationContext.getPrice(), calculationContext.getQuantity());
        }

        BigDecimal discount = floorEntry
                .getValue()
                .divide(BigDecimal.valueOf(100));
        log.info("discount=" + discount);
        BigDecimal multiplicand = BigDecimal.ONE.subtract(discount);
        BigDecimal priceWithDiscount = calculationContext.getPrice()
                .multiply(multiplicand);
        log.info("priceWithDiscount=" + priceWithDiscount);
        return multiple(priceWithDiscount, calculationContext.getQuantity());
    }

    private static BigDecimal multiple(BigDecimal calculationContext, int quantity) {
        return calculationContext.multiply(BigDecimal.valueOf(quantity))
                .setScale(DEFAULT_SCALE, RoundingMode.CEILING);
    }
}
