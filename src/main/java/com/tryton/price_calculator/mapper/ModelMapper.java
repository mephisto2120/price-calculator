package com.tryton.price_calculator.mapper;

public interface ModelMapper<Entity, DTO> {
	Entity toEntity(DTO dto);
}
