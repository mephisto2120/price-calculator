package com.tryton.price_calculator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
public class BaseParameters {
    @Schema(example = "abc-123")
    protected String id;
    @Schema(example = "Sample description")
    protected String description;
    protected LocalDateTime createdAt;
    @Schema(example = "mephisto2120")
    protected String createdBy;
    protected LocalDateTime modifiedAt;
    @Schema(example = "mephisto2120")
    protected String modifiedBy;
}
