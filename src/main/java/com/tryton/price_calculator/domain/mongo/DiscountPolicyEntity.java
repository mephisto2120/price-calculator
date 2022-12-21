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
@Document(collection = "discount_policy")
public class DiscountPolicyEntity {
	@Id
	private String id;
	private String name;
	private String description;
	private String createdAt;
	private String createdBy;
	private String modifiedAt;
	private String modifiedBy;
}
