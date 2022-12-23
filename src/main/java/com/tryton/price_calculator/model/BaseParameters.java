package com.tryton.price_calculator.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
public class BaseParameters {
    protected String id;
    protected String description;
    protected LocalDateTime createdAt;
    protected String createdBy;
    protected LocalDateTime modifiedAt;
    protected String modifiedBy;
}
