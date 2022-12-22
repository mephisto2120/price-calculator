package com.tryton.price_calculator.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.TreeMap;

@SuperBuilder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AmountDiscountPolicyConfig extends BaseParameters {
    @NotBlank(message = "discountMap is mandatory")
    private TreeMap<Integer, BigDecimal> discountMap;
}
