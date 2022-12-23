package com.tryton.price_calculator.repository.mongo;

import com.tryton.price_calculator.domain.mongo.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mongounit.AssertMatchesDataset;
import org.mongounit.MongoUnitTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static com.tryton.price_calculator.service.discount.DiscountPolicyConstants.DEFAULT_SCALE;

@SpringBootTest(properties = "spring.data.mongodb.port=27117")
@MongoUnitTest(name = "productRepository")
@DisplayName("productRepository with MongoUnit testing framework")
@ActiveProfiles("test")
class ProductRepositoryIntegrationTest {
    private static final BigDecimal PRICE = BigDecimal.TEN.setScale(DEFAULT_SCALE);
    private static final String ID = UUID.randomUUID().toString();
    private static final String CREATED_AT = "2022-12-02T12:20:30";
    private static final String USER_ID = "123";
    private static final String PRODUCT_NAME = "someSuperProduct";

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("Create product on an empty database")
    @AssertMatchesDataset
    void shouldFindProductAmongAllWhenOneCreated() {
        ProductEntity productEntity = ProductEntity.builder()
                .id(ID)
                .name(PRODUCT_NAME)
                .price(PRICE)
                .createdAt(CREATED_AT)
                .modifiedAt(CREATED_AT)
                .createdBy(USER_ID)
                .modifiedBy(USER_ID)
                .build();

        productRepository.save(productEntity);
    }
}
