package com.tryton.price_calculator.service.selector;

import com.tryton.price_calculator.service.discount.AmountBasedDiscountPolicy;
import com.tryton.price_calculator.service.discount.DiscountPolicy;
import com.tryton.price_calculator.service.discount.NoDiscountPolicy;
import com.tryton.price_calculator.service.discount.PercentageBasedDiscountPolicy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConfigBasedDiscountPolicySelector implements DiscountPolicySelector {
    private final String appliedPolicyName;
    private final PercentageBasedDiscountPolicy percentageBasedDiscountPolicy;
    private final AmountBasedDiscountPolicy amountBasedDiscountPolicy;
    private final NoDiscountPolicy noDiscountPolicy;

    @Override
    public DiscountPolicy select() {
        if ("percentage".equalsIgnoreCase(appliedPolicyName)) {
            return percentageBasedDiscountPolicy;
        } else if ("amount".equalsIgnoreCase(appliedPolicyName)) {
            return amountBasedDiscountPolicy;
        }
        return noDiscountPolicy;
    }
}
