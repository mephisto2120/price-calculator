package com.tryton.price_calculator.service;

import com.tryton.price_calculator.domain.mongo.ProductEntity;
import com.tryton.price_calculator.exception.handler.ProductNotFoundException;
import com.tryton.price_calculator.mapper.ProductMapper;
import com.tryton.price_calculator.model.Product;
import com.tryton.price_calculator.repository.mongo.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Product getProduct(String uuid) {
        ProductEntity productEntity = productRepository.findById(uuid)
                .orElseThrow(() -> new ProductNotFoundException("Product with id=" + uuid + " was not found"));
        return productMapper.toDto(productEntity);
    }

    public String addProduct(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        productRepository.save(productEntity);
        return productEntity.getId();
    }
}
