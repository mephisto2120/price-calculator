package com.tryton.price_calculator.repository.mongo;

import com.tryton.price_calculator.domain.mongo.PercentageDiscountPolicyConfigEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PercentageDiscountPolicyConfigRepository extends MongoRepository<PercentageDiscountPolicyConfigEntity, String> {
}
