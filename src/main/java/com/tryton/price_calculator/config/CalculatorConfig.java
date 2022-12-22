package com.tryton.price_calculator.config;

import com.tryton.price_calculator.mapper.ProductMapper;
import com.tryton.price_calculator.repository.mongo.DiscountPolicyRepository;
import com.tryton.price_calculator.repository.mongo.ProductRepository;
import com.tryton.price_calculator.service.ProductService;
import com.tryton.price_calculator.service.selector.ConfigBasedDiscountPolicySelector;
import com.tryton.price_calculator.service.selector.DiscountPolicySelector;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorConfig {

    @Value("calculator.appliedPolicyName")
    private String appliedPolicyName;

    @Autowired
    private DiscountPolicyRepository discountPolicyRepository;
    @Autowired
    private ProductRepository productRepository;

    @Bean
    public ProductService productService() {
        return new ProductService(productRepository, Mappers.getMapper(ProductMapper.class));
    }
/*
    @Bean
    public DiscountPolicySelector configBasedDiscountPolicySelector() {
        return new ConfigBasedDiscountPolicySelector(productRepository, Mappers.getMapper(ProductMapper.class));
    }*/
}
