package com.tryton.price_calculator.controller;

import com.tryton.price_calculator.model.Product;
import com.tryton.price_calculator.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shop/v1")
@CommonsLog
@Tag(name = "ProductController", description = "Enables adding and getting products")
public class ProductController {
    private final ProductService productService;

    @GetMapping(path = "/products")
    @Operation(summary = "Retrieves info about product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieves info about product",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "In case of validation error",
                    content = @Content)})
    public Product getProduct(@RequestParam String uuid) {
        log.info("Starting getProduct.");
        Product product = productService.getProduct(uuid);
        log.info("Finished getProduct.");
        return product;
    }

    @PostMapping(path = "/products")
    @Operation(summary = "Adding a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieves info about product",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "In case of validation error",
                    content = @Content)})
    public ResponseEntity<String> addProduct(@Valid @RequestBody Product product) {
        log.info("Starting addProduct.");
        String id = productService.addProduct(product);
        log.info("Finished addProduct.");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }
}
