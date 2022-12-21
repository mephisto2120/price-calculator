package com.tryton.price_calculator.config;

import com.tryton.price_calculator.repository.mongo.DiscountPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountPolicyConfig {

	@Autowired
	private DiscountPolicyRepository discountPolicyRepository;
}
