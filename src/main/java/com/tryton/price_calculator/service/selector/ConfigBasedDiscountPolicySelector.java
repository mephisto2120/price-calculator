package com.tryton.price_calculator.service.selector;

import com.tryton.price_calculator.service.discount.DiscountPolicy;
import com.tryton.price_calculator.service.discount.DiscountPolicyProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@RequiredArgsConstructor
@CommonsLog
public class ConfigBasedDiscountPolicySelector implements DiscountPolicySelector {
    private final String appliedPolicyName;
    private final DiscountPolicyProvider percentageBasedDiscountPolicyProvider;
    private final DiscountPolicyProvider amountBasedDiscountPolicyProvider;
    private final DiscountPolicyProvider noDiscountPolicyProvider;

    @Override
    public DiscountPolicy select() {
        log.info("appliedPolicyName=" + appliedPolicyName);

        if ("percentage".equalsIgnoreCase(appliedPolicyName)) {
            return percentageBasedDiscountPolicyProvider.provide();
        } else if ("amount".equalsIgnoreCase(appliedPolicyName)) {
            return amountBasedDiscountPolicyProvider.provide();
        }
        return noDiscountPolicyProvider.provide();
    }
}
