package com.tryton.price_calculator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@SuperBuilder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AppliedDiscountPolicyConfig extends BaseParameters {
    @Schema(example = "amount", allowableValues = {"noPolicy", "amount", "percentage"})
    @NotBlank(message = "policyName is mandatory")
    private String policyName;
}
