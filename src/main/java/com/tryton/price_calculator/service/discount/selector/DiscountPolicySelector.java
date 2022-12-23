package com.tryton.price_calculator.service.discount.selector;

import com.tryton.price_calculator.service.discount.DiscountPolicy;

public interface DiscountPolicySelector {
    DiscountPolicy select();
}
