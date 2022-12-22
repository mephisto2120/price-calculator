package com.tryton.price_calculator.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.TreeMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "amount_discount_policy")
public class AmountDiscountPolicyConfigEntity {
    @Id
    private String id;
    private TreeMap<Integer, BigDecimal> discountMap;
    private String description;
    private String createdAt;
    private String createdBy;
    private String modifiedAt;
    private String modifiedBy;
}