package com.tryton.price_calculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
public class DiscountPolicy {
	protected String id;
	protected String description;
	protected LocalDateTime createdAt;
	protected String createdBy;
	protected LocalDateTime modifiedAt;
	protected String modifiedBy;
}
