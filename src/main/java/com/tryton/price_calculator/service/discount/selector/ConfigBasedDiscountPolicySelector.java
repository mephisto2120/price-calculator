package com.tryton.price_calculator.service.discount.selector;

import com.tryton.price_calculator.service.discount.AppliedDiscountPolicyService;
import com.tryton.price_calculator.service.discount.DiscountPolicy;
import com.tryton.price_calculator.service.discount.DiscountPolicyProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@RequiredArgsConstructor
@CommonsLog
public class ConfigBasedDiscountPolicySelector implements DiscountPolicySelector {
    private final DiscountPolicyProvider percentageBasedDiscountPolicyProvider;
    private final DiscountPolicyProvider amountBasedDiscountPolicyProvider;
    private final DiscountPolicyProvider noDiscountPolicyProvider;
    private final AppliedDiscountPolicyService appliedDiscountPolicyService;

    @Override
    public DiscountPolicy select() {
        String selectedPolicyName = appliedDiscountPolicyService.get()
                .getPolicyName();
        log.info("selectedPolicyName=" + selectedPolicyName);
        if ("percentage".equalsIgnoreCase(selectedPolicyName)) {
            return percentageBasedDiscountPolicyProvider.provide();
        } else if ("amount".equalsIgnoreCase(selectedPolicyName)) {
            return amountBasedDiscountPolicyProvider.provide();
        }
        return noDiscountPolicyProvider.provide();
    }
}
