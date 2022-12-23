package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.domain.mongo.AppliedDiscountPolicyConfigEntity;
import com.tryton.price_calculator.exception.handler.PolicyNotFoundException;
import com.tryton.price_calculator.mapper.AppliedDiscountPolicyConfigMapper;
import com.tryton.price_calculator.model.AppliedDiscountPolicyConfig;
import com.tryton.price_calculator.repository.mongo.AppliedDiscountPolicyConfigRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@RequiredArgsConstructor
@CommonsLog
public class AppliedDiscountPolicyService {
    private static final String DEFAULT_POLICY = "default-policy";

    private final AppliedDiscountPolicyConfigRepository appliedDiscountPolicyConfigRepository;
    private final AppliedDiscountPolicyConfigMapper appliedDiscountPolicyConfigMapper;

    public void upsert(AppliedDiscountPolicyConfig policy) {
        AppliedDiscountPolicyConfigEntity configEntity = appliedDiscountPolicyConfigMapper.toEntity(policy);
        configEntity.setId(DEFAULT_POLICY);
        appliedDiscountPolicyConfigRepository.deleteById(DEFAULT_POLICY);
        appliedDiscountPolicyConfigRepository.save(configEntity);
        log.info("Policy has been updated= " + policy);
    }

    public AppliedDiscountPolicyConfig get() {
        AppliedDiscountPolicyConfigEntity policyEntity = appliedDiscountPolicyConfigRepository.findById(DEFAULT_POLICY)
                .orElseThrow(() -> new PolicyNotFoundException("Policy " + DEFAULT_POLICY + " was not found"));
        return appliedDiscountPolicyConfigMapper.toDto(policyEntity);
    }
}
