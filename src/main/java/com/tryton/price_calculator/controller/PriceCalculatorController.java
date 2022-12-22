package com.tryton.price_calculator.controller;

import com.tryton.price_calculator.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop/v1/price-calculator")
@CommonsLog
@Tag(name = "PriceCalculatorController", description = "Enables calculating prices for products which will be delivered")
public class PriceCalculatorController {
    @GetMapping(path = "/calculate")
    @Operation(summary = "Calculates price for delivering parcels")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieves info about product",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "In case of validation error",
                    content = @Content)})
    public Product calculate(@RequestParam String productUuid) {
        log.info("Starting calculate.");
        //call service
        log.info("Finished calculate.");
        return null;
    }
}
