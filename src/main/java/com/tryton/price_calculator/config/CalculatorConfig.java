package com.tryton.price_calculator.config;

import com.tryton.price_calculator.mapper.AmountDiscountPolicyConfigMapper;
import com.tryton.price_calculator.mapper.PercentageDiscountPolicyConfigMapper;
import com.tryton.price_calculator.mapper.ProductMapper;
import com.tryton.price_calculator.repository.mongo.AmountDiscountPolicyConfigRepository;
import com.tryton.price_calculator.repository.mongo.DiscountPolicyRepository;
import com.tryton.price_calculator.repository.mongo.PercentageDiscountPolicyConfigRepository;
import com.tryton.price_calculator.repository.mongo.ProductRepository;
import com.tryton.price_calculator.service.AmountDiscountPolicyService;
import com.tryton.price_calculator.service.PercentageDiscountPolicyService;
import com.tryton.price_calculator.service.ProductService;
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

    @Autowired
    private PercentageDiscountPolicyConfigRepository percentageDiscountPolicyConfigRepository;
    @Autowired
    private AmountDiscountPolicyConfigRepository amountDiscountPolicyConfigRepository;

    @Bean
    public ProductService productService() {
        return new ProductService(productRepository, Mappers.getMapper(ProductMapper.class));
    }

    @Bean
    public PercentageDiscountPolicyService percentageDiscountPolicyService() {
        return new PercentageDiscountPolicyService(percentageDiscountPolicyConfigRepository,
                Mappers.getMapper(PercentageDiscountPolicyConfigMapper.class));
    }

    @Bean
    public AmountDiscountPolicyService amountDiscountPolicyService() {
        return new AmountDiscountPolicyService(amountDiscountPolicyConfigRepository,
                Mappers.getMapper(AmountDiscountPolicyConfigMapper.class));
    }

/*
    @Bean
    public DiscountPolicySelector configBasedDiscountPolicySelector() {
        return new ConfigBasedDiscountPolicySelector(productRepository, Mappers.getMapper(ProductMapper.class));
    }*/
}
