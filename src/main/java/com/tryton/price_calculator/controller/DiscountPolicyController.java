package com.tryton.price_calculator.controller;

import com.tryton.price_calculator.model.AmountDiscountPolicyConfig;
import com.tryton.price_calculator.model.PercentageDiscountPolicyConfig;
import com.tryton.price_calculator.service.discount.AmountDiscountPolicyService;
import com.tryton.price_calculator.service.discount.PercentageDiscountPolicyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/v1")
@CommonsLog
@Tag(name = "DiscountPolicyController", description = "Enables operations on discount policy")
public class DiscountPolicyController {

    private final PercentageDiscountPolicyService percentageDiscountPolicyService;
    private final AmountDiscountPolicyService amountDiscountPolicyService;

    @GetMapping(path = "/discount-policy/percentage")
    @Operation(summary = "Retrieves info about percentage discount policy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieves info about discount policy",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "In case of validation error",
                    content = @Content)})
    public PercentageDiscountPolicyConfig getPercentageDiscountPolicy() {
        log.info("Starting getPercentageDiscountPolicy.");
        PercentageDiscountPolicyConfig policyConfig = percentageDiscountPolicyService.get();
        log.info("Finished getPercentageDiscountPolicy.");
        return policyConfig;
    }

    @PutMapping(path = "/discount-policy/percentage")
    @Operation(summary = "Updates current percentage discount policy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updates current discount policy",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "In case of validation error",
                    content = @Content)})
    @ResponseStatus(HttpStatus.CREATED)
    public void updatePercentageDiscountPolicy(@Valid @RequestBody PercentageDiscountPolicyConfig discountPolicy) {
        log.info("Starting updatePercentageDiscountPolicy.");
        percentageDiscountPolicyService.upsert(discountPolicy);
        log.info("Finished updatePercentageDiscountPolicy.");
    }

    @GetMapping(path = "/discount-policy/amount")
    @Operation(summary = "Retrieves info about amount discount policy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieves info about discount policy",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "In case of validation error",
                    content = @Content)})
    public AmountDiscountPolicyConfig getAmountDiscountPolicy() {
        log.info("Starting getAmountDiscountPolicy.");
        AmountDiscountPolicyConfig policyConfig = amountDiscountPolicyService.get();
        log.info("Finished getAmountDiscountPolicy.");
        return policyConfig;
    }

    @PutMapping(path = "/discount-policy/amount")
    @Operation(summary = "Updates current amount discount policy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updates current discount policy",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "In case of validation error",
                    content = @Content)})
    @ResponseStatus(HttpStatus.CREATED)
    public void updateAmmountDiscountPolicy(@Valid @RequestBody AmountDiscountPolicyConfig discountPolicy) {
        log.info("Starting updatePercentageDiscountPolicy.");
        amountDiscountPolicyService.upsert(discountPolicy);
        log.info("Finished updatePercentageDiscountPolicy.");
    }
}
