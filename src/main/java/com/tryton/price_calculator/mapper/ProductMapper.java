package com.tryton.price_calculator.mapper;

import com.tryton.price_calculator.domain.mongo.ProductEntity;
import com.tryton.price_calculator.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(imports = UUID.class)
public interface ProductMapper extends ModelMapper<ProductEntity, Product> {
	@Mapping(target = "id", defaultExpression = "java( UUID.randomUUID().toString() )")
	ProductEntity toEntity(Product dto);
}
