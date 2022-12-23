package com.tryton.price_calculator.service;

import com.tryton.price_calculator.domain.mongo.ProductEntity;
import com.tryton.price_calculator.exception.handler.ProductNotFoundException;
import com.tryton.price_calculator.mapper.ProductMapper;
import com.tryton.price_calculator.model.Product;
import com.tryton.price_calculator.repository.mongo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    private static final String UUID = "abc-123";

    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private ProductMapper productMapperMock;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldGetProduct() {
        //given
        ProductEntity productEntity = createProductEntity();
        Optional<ProductEntity> optionalProductEntity = Optional.of(productEntity);
        given(productRepositoryMock.findById(UUID)).willReturn(optionalProductEntity);
        Product expectedProduct = createProduct();
        given(productMapperMock.toDto(productEntity)).willReturn(expectedProduct);

        //when
        Product product = productService.getProduct(UUID);

        //then
        assertThat(product).isEqualTo(expectedProduct);
        then(productRepositoryMock).should().findById(UUID);
        then(productMapperMock).should().toDto(productEntity);
    }

    private static ProductEntity createProductEntity() {
        return ProductEntity.builder()
                .id(UUID)
                .build();
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        //given
        given(productRepositoryMock.findById(UUID)).willReturn(Optional.empty());

        //when
        Throwable thrown = catchThrowable(() -> productService.getProduct(UUID));

        //then
        assertThat(thrown)
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product with id=abc-123 was not found");
        then(productRepositoryMock).should().findById(UUID);
        then(productMapperMock).shouldHaveNoInteractions();
    }

    @Test
    void addProduct() {
        //given
        ProductEntity productEntity = createProductEntity();

        Product product = createProduct();
        given(productMapperMock.toEntity(product)).willReturn(productEntity);

        //when
        productService.addProduct(product);

        //then
        then(productRepositoryMock).should().save(productEntity);
        then(productMapperMock).should().toEntity(product);
    }

    private static Product createProduct() {
        return Product.builder()
                .id(UUID)
                .build();
    }
}
