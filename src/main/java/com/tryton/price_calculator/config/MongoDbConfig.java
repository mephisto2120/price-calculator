package com.tryton.price_calculator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.tryton.price_calculator.repository.mongo")
public class MongoDbConfig {
}
