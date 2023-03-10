package com.tryton.price_calculator.controller;

import com.tryton.price_calculator.service.PriceCalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/v1/price-calculator")
@CommonsLog
@Tag(name = "PriceCalculatorController", description = "Enables calculating prices for products which will be delivered")
public class PriceCalculatorController {

    private final PriceCalculatorService priceCalculatorService;

    @GetMapping(path = "/calculate")
    @Operation(summary = "Calculates price for purchase")
    @Parameters({
            @Parameter(name = "productId", example = "abc-123"),
            @Parameter(name = "amount", example = "10")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculates price for purchase",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "In case of validation error",
                    content = @Content)})
    public BigDecimal calculate(@RequestParam String productId, @RequestParam Integer amount) {
        log.info("Starting calculate.");
        BigDecimal calculatedPrice = priceCalculatorService.calculate(productId, amount);
        log.info("Finished calculate.");
        return calculatedPrice;
    }
}
