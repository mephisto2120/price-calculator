package com.tryton.price_calculator.controller;

import com.tryton.price_calculator.Profile;
import com.tryton.price_calculator.model.Product;
import com.tryton.price_calculator.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.tryton.price_calculator.service.discount.DiscountPolicyConstants.DEFAULT_SCALE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.then;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ActiveProfiles(Profile.TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerIntegrationTest {
    private static final String PRODUCT_URL = "/shop/v1/products";
    private static final String PRODUCT_ID_PARAMETER = "productId";
    private static final String PRODUCT_ID = "abc-123";
    private static final String USER = "HikaruAppUser";
    private static final String PRODUCT_NAME = "someSuperProduct";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2022, 12, 5, 12, 20, 30);
    private static final BigDecimal PRICE = BigDecimal.TEN.setScale(DEFAULT_SCALE);
    private static final String EXPECTED_PRODUCT_JSON = "{\"id\":null,\"description\":null,\"createdAt\":\"2022-12-05T12:20:30\",\"createdBy\":\"HikaruAppUser\",\"modifiedAt\":\"2022-12-05T12:20:30\",\"modifiedBy\":\"HikaruAppUser\",\"name\":\"someSuperProduct\",\"price\":10.00}";

    @MockBean
    private ProductService productServiceMock;
    @LocalServerPort
    private int port;

    @Test
    void shouldReturnBadRequestWhenQueryParametersAreNotGiven() {
        given().port(port)
                .when().get(PRODUCT_URL)
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void shouldReturnOK() {
        Product product = validProduct();
        org.mockito.BDDMockito.given(productServiceMock.getProduct(PRODUCT_ID)).willReturn(product);

        given().port(port)
                .queryParam(PRODUCT_ID_PARAMETER, PRODUCT_ID)
                .when().get(PRODUCT_URL)
                .then().statusCode(HttpStatus.OK.value())
                .assertThat().body(equalTo(EXPECTED_PRODUCT_JSON));
    }

    private static Product validProduct() {
        Product product = Product.builder()
                .name(PRODUCT_NAME)
                .price(PRICE)
                .createdBy(USER)
                .modifiedBy(USER)
                .createdAt(CREATED_AT)
                .modifiedAt(CREATED_AT)
                .build();
        return product;
    }

    @Test
    void shouldReturnUnsupportedMediaTypeWhenRequestBodyIsNull() {
        given().port(port)
                .when().post(PRODUCT_URL)
                .then().statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

    @Test
    void shouldReturnBadRequestBodyIsNotGiven() {
        Product product = Product.builder()
                .build();
        given().port(port)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(product)
                .when().post(PRODUCT_URL)
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void shouldReturnCreated() {
        Product product = validProduct();
        org.mockito.BDDMockito.given(productServiceMock.addProduct(product)).willReturn(PRODUCT_ID);

        given().port(port)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(product)
                .when().post(PRODUCT_URL)
                .then().statusCode(HttpStatus.CREATED.value())
                .assertThat().body(equalTo(PRODUCT_ID));

        then(productServiceMock).should().addProduct(product);
    }
}
