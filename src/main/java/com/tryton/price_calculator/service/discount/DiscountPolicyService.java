package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.domain.mongo.DiscountPolicyEntity;
import com.tryton.price_calculator.mapper.DiscountPolicyMapper;
import com.tryton.price_calculator.model.DiscountPolicy;
import com.tryton.price_calculator.repository.mongo.DiscountPolicyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiscountPolicyService {
	private final DiscountPolicyMapper discountPolicyMapper;
	private final DiscountPolicyRepository discountPolicyRepository;

	public void create(DiscountPolicy message) {
		DiscountPolicyEntity discountPolicyEntity = discountPolicyMapper.toEntity(message);
		discountPolicyRepository.save(discountPolicyEntity);
	}
}
