package com.tryton.price_calculator.mapper;

import com.tryton.price_calculator.domain.mongo.PercentageDiscountPolicyConfigEntity;
import com.tryton.price_calculator.model.PercentageDiscountPolicyConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(imports = UUID.class)
public interface PercentageDiscountPolicyConfigMapper extends ModelMapper<PercentageDiscountPolicyConfigEntity, PercentageDiscountPolicyConfig> {
    @Mapping(target = "id", defaultExpression = "java( UUID.randomUUID().toString() )")
    PercentageDiscountPolicyConfigEntity toEntity(PercentageDiscountPolicyConfig dto);
}
