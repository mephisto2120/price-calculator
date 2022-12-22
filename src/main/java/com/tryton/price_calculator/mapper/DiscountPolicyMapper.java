package com.tryton.price_calculator.mapper;

import com.tryton.price_calculator.domain.mongo.DiscountPolicyEntity;
import com.tryton.price_calculator.model.DiscountPolicy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(imports = UUID.class)
public interface DiscountPolicyMapper extends ModelMapper<DiscountPolicyEntity, DiscountPolicy> {
	@Mapping(target = "id", defaultExpression = "java( UUID.randomUUID().toString() )")
	DiscountPolicyEntity toEntity(DiscountPolicy dto);
}
