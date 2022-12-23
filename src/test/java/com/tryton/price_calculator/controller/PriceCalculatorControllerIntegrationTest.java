package com.tryton.price_calculator.controller;

import com.tryton.price_calculator.Profile;
import com.tryton.price_calculator.service.PriceCalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static com.tryton.price_calculator.service.discount.DiscountPolicyConstants.DEFAULT_SCALE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles(Profile.TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceCalculatorControllerIntegrationTest {
    private static final String CALCULATE_URL = "/shop/v1/price-calculator/calculate";
    private static final String PRODUCT_ID = "abc-123";
    private static final Integer AMOUNT = 10;
    private static final String PRODUCT_ID_PARAMETER = "productId";
    private static final String AMOUNT_PARAMETER = "amount";

    @MockBean
    private PriceCalculatorService priceCalculatorServiceMock;

    @LocalServerPort
    private int port;

    @Test
    void shouldReturnBadRequestWhenQueryParametersAreNotGiven() {
        given().port(port)
                .when().get(CALCULATE_URL)
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void shouldReturnOkWhileCalculating() {
        BigDecimal calculatedPrice = BigDecimal.valueOf(100).setScale(DEFAULT_SCALE);
        org.mockito.BDDMockito.given(priceCalculatorServiceMock.calculate(PRODUCT_ID, AMOUNT)).willReturn(calculatedPrice);

        given().port(port)
                .queryParam(PRODUCT_ID_PARAMETER, PRODUCT_ID)
                .queryParam(AMOUNT_PARAMETER, AMOUNT)
                .when().get(CALCULATE_URL)
                .then().statusCode(HttpStatus.OK.value())
                .assertThat().body(equalTo(calculatedPrice.toString()));
    }

    @Test
    void shouldReturnBadRequestWhenAmountNotGiven() {
        given().port(port)
                .queryParam(PRODUCT_ID_PARAMETER, PRODUCT_ID)
                .when().get(CALCULATE_URL)
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void shouldReturnBadRequestWhenProductIdtNotGiven() {
        given().port(port)
                .queryParam(AMOUNT_PARAMETER, AMOUNT)
                .when().get(CALCULATE_URL)
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
