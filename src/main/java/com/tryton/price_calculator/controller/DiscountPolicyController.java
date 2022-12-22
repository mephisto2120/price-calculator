package com.tryton.price_calculator.controller;

import com.tryton.price_calculator.model.DiscountPolicy;
import com.tryton.price_calculator.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/shop/v1")
@CommonsLog
@Tag(name = "DiscountPolicyController", description = "Enables operations on discount policy")
public class DiscountPolicyController {

    @GetMapping(path = "/discount-policy")
    @Operation(summary = "Retrieves info about discount policy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieves info about discount policy",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "In case of validation error",
                    content = @Content)})
    public Product getDiscountPolicy() {
        log.info("Starting getDiscountPolicy.");
        //call service
        log.info("Finished getDiscountPolicy.");
        return null;
    }

    @PutMapping(path = "/discount-policy")
    @Operation(summary = "Updates current discount policy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updates current discount policy",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "In case of validation error",
                    content = @Content)})
    @ResponseStatus(HttpStatus.CREATED)
    public void updateDiscountPolicy(@Valid @RequestBody DiscountPolicy discountPolicy) {
        log.info("Starting updateDiscountPolicy.");
        log.info("Finished updateDiscountPolicy.");
    }
}
