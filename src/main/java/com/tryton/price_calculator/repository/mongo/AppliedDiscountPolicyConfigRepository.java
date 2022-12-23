package com.tryton.price_calculator.repository.mongo;

import com.tryton.price_calculator.domain.mongo.AppliedDiscountPolicyConfigEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppliedDiscountPolicyConfigRepository  extends MongoRepository<AppliedDiscountPolicyConfigEntity, String> {
}
