package com.tryton.price_calculator.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
public class DiscountPolicy {
	protected String id;
	@NotBlank(message = "name is mandatory")
	protected String name;
	protected LocalDateTime createdAt;
	protected String createdBy;
	protected LocalDateTime modifiedAt;
	protected String modifiedBy;
}
