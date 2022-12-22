package com.tryton.price_calculator.repository.mongo;

import com.tryton.price_calculator.domain.mongo.DiscountPolicyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiscountPolicyRepository extends MongoRepository<DiscountPolicyEntity, String> {
}
