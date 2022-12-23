package com.tryton.price_calculator.mapper;

import com.tryton.price_calculator.domain.mongo.ProductEntity;
import com.tryton.price_calculator.model.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.tryton.price_calculator.service.discount.DiscountPolicyConstants.DEFAULT_SCALE;
import static org.assertj.core.api.Assertions.assertThat;

class ProductMapperTest {
    private static final String USER = "HikaruAppUser";
    private static final String PRODUCT_NAME = "someSuperProduct";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2022, 12, 5, 12, 20, 30);
    private static final String CREATED_AT_STR = "2022-12-05T12:20:30";
    private static final BigDecimal PRICE = BigDecimal.TEN.setScale(DEFAULT_SCALE);

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void shouldMapToNullIfNullGiven() {
        //given
        Product product = null;

        //when
        ProductEntity productEntity = productMapper.toEntity(product);

        //then
        assertThat(productEntity).isNull();
    }

    @Test
    void shouldMapToEntityWhenDatesNotFilled() {
        //given
        Product product = createProduct()
                .build();

        //when
        ProductEntity productEntity = productMapper.toEntity(product);

        //then
        assertCommonFields(productEntity);
        assertThat(productEntity.getCreatedAt()).isNull();
        assertThat(productEntity.getModifiedAt()).isNull();
    }

    @Test
    void shouldMapToEntity() {
        //given
        Product product = createProduct()
                .createdAt(CREATED_AT)
                .modifiedAt(CREATED_AT)
                .build();

        //when
        ProductEntity productEntity = productMapper.toEntity(product);

        //then
        assertCommonFields(productEntity);
        assertThat(productEntity.getCreatedAt()).isEqualTo(CREATED_AT_STR);
        assertThat(productEntity.getModifiedAt()).isEqualTo(CREATED_AT_STR);
    }

    private static Product.ProductBuilder<?, ?> createProduct() {
        return Product.builder()
                .name(PRODUCT_NAME)
                .price(PRICE)
                .createdBy(USER)
                .modifiedBy(USER);
    }

    private static void assertCommonFields(ProductEntity productEntity) {
        assertThat(productEntity).isNotNull();
        assertThat(productEntity.getId()).isNotNull();
        assertThat(productEntity.getName()).isEqualTo(PRODUCT_NAME);
        assertThat(productEntity.getPrice()).isEqualTo(PRICE);
        assertThat(productEntity.getCreatedBy()).isEqualTo(USER);
        assertThat(productEntity.getModifiedBy()).isEqualTo(USER);
    }
}
