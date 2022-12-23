package com.tryton.price_calculator.service;

import com.tryton.price_calculator.model.Product;
import com.tryton.price_calculator.service.discount.CalculationContext;
import com.tryton.price_calculator.service.discount.DiscountPolicy;
import com.tryton.price_calculator.service.discount.selector.DiscountPolicySelector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.tryton.price_calculator.service.discount.DiscountPolicyConstants.DEFAULT_SCALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PriceCalculatorServiceTest {

    private static final String PRODUCT_ID = "abc-123";
    private static final int AMOUNT = 10;
    private static final BigDecimal EXPECTED_PRICE = BigDecimal.TEN.setScale(DEFAULT_SCALE);

    @Mock
    private DiscountPolicy discountPolicyMock;

    @Mock
    private DiscountPolicySelector discountPolicySelectorMock;
    @Mock
    private ProductService productServiceMock;

    @InjectMocks
    private PriceCalculatorService priceCalculatorService;

    @Test
    void shouldCalculatePrice() {
        //given
        given(discountPolicySelectorMock.select()).willReturn(discountPolicyMock);
        given(productServiceMock.getProduct(PRODUCT_ID)).willReturn(createProduct());
        given(discountPolicyMock.applyDiscount(any(CalculationContext.class))).willReturn(EXPECTED_PRICE);

        //when
        BigDecimal calculatedPrice = priceCalculatorService.calculate(PRODUCT_ID, AMOUNT);

        //then
        assertThat(calculatedPrice).isEqualTo(EXPECTED_PRICE);
        then(discountPolicySelectorMock).should().select();
        then(productServiceMock).should().getProduct(PRODUCT_ID);
        then(discountPolicyMock).should().applyDiscount(any(CalculationContext.class));
    }

    private static Product createProduct() {
        return Product.builder()
                .id(PRODUCT_ID)
                .build();
    }
}
