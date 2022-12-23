package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.domain.mongo.AmountDiscountPolicyConfigEntity;
import com.tryton.price_calculator.exception.handler.PolicyNotFoundException;
import com.tryton.price_calculator.mapper.AmountDiscountPolicyConfigMapper;
import com.tryton.price_calculator.model.AmountDiscountPolicyConfig;
import com.tryton.price_calculator.repository.mongo.AmountDiscountPolicyConfigRepository;
import com.tryton.price_calculator.service.discount.AmountBasedDiscountPolicy;
import com.tryton.price_calculator.service.discount.DiscountPolicy;
import com.tryton.price_calculator.service.discount.DiscountPolicyProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@RequiredArgsConstructor
@CommonsLog
public class AmountDiscountPolicyService implements DiscountPolicyProvider {
    private static final String DEFAULT_POLICY = "default-policy";

    private final AmountDiscountPolicyConfigRepository amountDiscountPolicyConfigRepository;
    private final AmountDiscountPolicyConfigMapper amountDiscountPolicyConfigMapper;

    public void upsert(AmountDiscountPolicyConfig policy) {
        AmountDiscountPolicyConfigEntity configEntity = amountDiscountPolicyConfigMapper.toEntity(policy);
        configEntity.setId(DEFAULT_POLICY);
        amountDiscountPolicyConfigRepository.deleteById(DEFAULT_POLICY);
        amountDiscountPolicyConfigRepository.save(configEntity);
        log.info("Policy has been updated= " + policy);
    }

    public AmountDiscountPolicyConfig get() {
        AmountDiscountPolicyConfigEntity policyEntity = amountDiscountPolicyConfigRepository.findById(DEFAULT_POLICY)
                .orElseThrow(() -> new PolicyNotFoundException("Policy " + DEFAULT_POLICY + " was not found"));
        return amountDiscountPolicyConfigMapper.toDto(policyEntity);
    }

    @Override
    public DiscountPolicy provide() {
        return new AmountBasedDiscountPolicy(get());
    }
}
