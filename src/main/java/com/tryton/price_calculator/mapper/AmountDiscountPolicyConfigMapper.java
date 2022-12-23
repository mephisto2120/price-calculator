package com.tryton.price_calculator.mapper;

import com.tryton.price_calculator.domain.mongo.AmountDiscountPolicyConfigEntity;
import com.tryton.price_calculator.model.AmountDiscountPolicyConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(imports = UUID.class)
public interface AmountDiscountPolicyConfigMapper extends ModelMapper<AmountDiscountPolicyConfigEntity, AmountDiscountPolicyConfig> {
    @Mapping(target = "id", defaultExpression = "java( UUID.randomUUID().toString() )")
    AmountDiscountPolicyConfigEntity toEntity(AmountDiscountPolicyConfig dto);
}
