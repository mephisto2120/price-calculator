package com.tryton.price_calculator.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "applied_discount_policy")
public class AppliedDiscountPolicyConfigEntity {
    @Id
    private String id;
    private String policyName;
    private String createdAt;
    private String createdBy;
    private String modifiedAt;
    private String modifiedBy;
}