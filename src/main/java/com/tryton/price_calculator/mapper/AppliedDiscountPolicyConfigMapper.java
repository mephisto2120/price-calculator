package com.tryton.price_calculator.mapper;

import com.tryton.price_calculator.domain.mongo.AppliedDiscountPolicyConfigEntity;
import com.tryton.price_calculator.model.AppliedDiscountPolicyConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(imports = UUID.class)
public interface AppliedDiscountPolicyConfigMapper extends ModelMapper<AppliedDiscountPolicyConfigEntity, AppliedDiscountPolicyConfig> {
    @Mapping(target = "id", defaultExpression = "java( UUID.randomUUID().toString() )")
    AppliedDiscountPolicyConfigEntity toEntity(AppliedDiscountPolicyConfig dto);
}
