package com.tryton.price_calculator.repository.mongo;

import com.tryton.price_calculator.domain.mongo.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
