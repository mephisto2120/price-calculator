package com.tryton.price_calculator.service.selector;

import com.tryton.price_calculator.service.discount.DiscountPolicy;

public interface DiscountPolicySelector {
    DiscountPolicy select();
}
