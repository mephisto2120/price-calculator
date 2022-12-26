package com.tryton.price_calculator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@SuperBuilder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseParameters {
    @Schema(example = "SampleProduct")
    @NotBlank(message = "name is mandatory")
    private String name;
    @Schema(example = "20.00")
    @Positive(message = "price must be positive")
    private BigDecimal price;
}
