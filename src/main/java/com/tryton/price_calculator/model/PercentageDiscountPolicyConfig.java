package com.tryton.price_calculator.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@SuperBuilder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PercentageDiscountPolicyConfig extends BaseParameters {
    @NotBlank(message = "discount is mandatory")
    private BigDecimal discount;
}

