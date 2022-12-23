package com.tryton.price_calculator.service;

import com.tryton.price_calculator.model.Product;
import com.tryton.price_calculator.service.discount.CalculationContext;
import com.tryton.price_calculator.service.discount.DefaultCalculationContext;
import com.tryton.price_calculator.service.discount.DiscountPolicy;
import com.tryton.price_calculator.service.selector.DiscountPolicySelector;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@CommonsLog
public class PriceCalculatorService {

    private final DiscountPolicySelector discountPolicySelector;
    private final ProductService productService;

    public BigDecimal calculate(@RequestParam String productId, @RequestParam Integer amount) {
        log.info("Starting calculate.");
        DiscountPolicy discountPolicy = discountPolicySelector.select();
        Product product = productService.getProduct(productId);
        CalculationContext calculationContext = DefaultCalculationContext.builder()
                .price(product.getPrice())
                .quantity(amount)
                .build();
        log.info("Built calculationContext= " + calculationContext);
        BigDecimal calculatedPrice = discountPolicy.applyDiscount(calculationContext);
        log.info("Calculated price= " + calculatedPrice);
        log.info("Finished calculate.");
        return calculatedPrice;
    }
}
