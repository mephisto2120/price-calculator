package com.tryton.price_calculator.service;

import com.tryton.price_calculator.domain.mongo.PercentageDiscountPolicyConfigEntity;
import com.tryton.price_calculator.exception.handler.PolicyNotFoundException;
import com.tryton.price_calculator.mapper.PercentageDiscountPolicyConfigMapper;
import com.tryton.price_calculator.model.PercentageDiscountPolicyConfig;
import com.tryton.price_calculator.repository.mongo.PercentageDiscountPolicyConfigRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PercentageDiscountPolicyService {
    private static final String DEFAULT_POLICY = "default-policy";

    private final PercentageDiscountPolicyConfigRepository percentageDiscountPolicyConfigRepository;
    private final PercentageDiscountPolicyConfigMapper percentageDiscountPolicyConfigMapper;

    public void upsert(PercentageDiscountPolicyConfig policy) {
        PercentageDiscountPolicyConfigEntity configEntity = percentageDiscountPolicyConfigMapper.toEntity(policy);
        configEntity.setId(DEFAULT_POLICY);
        percentageDiscountPolicyConfigRepository.deleteById(DEFAULT_POLICY);
        percentageDiscountPolicyConfigRepository.save(configEntity);
    }

    public PercentageDiscountPolicyConfig get() {
        PercentageDiscountPolicyConfigEntity policyEntity = percentageDiscountPolicyConfigRepository.findById(DEFAULT_POLICY)
                .orElseThrow(() -> new PolicyNotFoundException("Policy " + DEFAULT_POLICY + " was not found"));
        return percentageDiscountPolicyConfigMapper.toDto(policyEntity);
    }
}
