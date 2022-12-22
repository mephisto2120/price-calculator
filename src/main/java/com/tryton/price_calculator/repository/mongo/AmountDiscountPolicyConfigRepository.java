package com.tryton.price_calculator.repository.mongo;

import com.tryton.price_calculator.domain.mongo.AmountDiscountPolicyConfigEntity;
import com.tryton.price_calculator.domain.mongo.PercentageDiscountPolicyConfigEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AmountDiscountPolicyConfigRepository extends MongoRepository<AmountDiscountPolicyConfigEntity, String> {
}
