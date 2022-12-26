package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.domain.mongo.AppliedDiscountPolicyConfigEntity;
import com.tryton.price_calculator.mapper.AppliedDiscountPolicyConfigMapper;
import com.tryton.price_calculator.model.AppliedDiscountPolicyConfig;
import com.tryton.price_calculator.repository.mongo.AppliedDiscountPolicyConfigRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@RequiredArgsConstructor
@CommonsLog
public class AppliedDiscountPolicyService {
    private static final String DEFAULT_POLICY = "default-policy";
    private static final String DEFAULT_USER = "mephisto2120";

    private final String defaultPolicyName;
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
        return appliedDiscountPolicyConfigRepository.findById(DEFAULT_POLICY)
                .map(appliedDiscountPolicyConfigMapper::toDto)
                .orElse(noDiscount());
    }

    private AppliedDiscountPolicyConfig noDiscount() {
        return AppliedDiscountPolicyConfig.builder()
                .id(DEFAULT_POLICY)
                .policyName(defaultPolicyName)
                .createdBy(DEFAULT_USER)
                .modifiedBy(DEFAULT_USER)
                .build();
    }
}
