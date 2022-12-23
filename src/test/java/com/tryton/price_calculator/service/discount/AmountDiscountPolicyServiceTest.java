package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.domain.mongo.AmountDiscountPolicyConfigEntity;
import com.tryton.price_calculator.exception.handler.PolicyNotFoundException;
import com.tryton.price_calculator.mapper.AmountDiscountPolicyConfigMapper;
import com.tryton.price_calculator.model.AmountDiscountPolicyConfig;
import com.tryton.price_calculator.repository.mongo.AmountDiscountPolicyConfigRepository;
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
class AmountDiscountPolicyServiceTest {
    private static final String DEFAULT_POLICY = "default-policy";

    @Mock
    private AmountDiscountPolicyConfigRepository amountDiscountPolicyConfigRepositoryMock;
    @Mock
    private AmountDiscountPolicyConfigMapper amountDiscountPolicyConfigMapperMock;

    @InjectMocks
    private AmountDiscountPolicyService amountDiscountPolicyService;


    @Test
    void shouldThrowExceptionWhenPolicyNotFound() {
        //given
        given(amountDiscountPolicyConfigRepositoryMock.findById(DEFAULT_POLICY)).willReturn(Optional.empty());

        //when
        Throwable thrown = catchThrowable(() -> amountDiscountPolicyService.get());

        //then
        assertThat(thrown)
                .isInstanceOf(PolicyNotFoundException.class)
                .hasMessage("Policy default-policy was not found");
        then(amountDiscountPolicyConfigRepositoryMock).should().findById(DEFAULT_POLICY);
        then(amountDiscountPolicyConfigMapperMock).shouldHaveNoInteractions();
    }

    @Test
    void shouldGetPolicyWhenFoundInDatabase() {
        //given
        AmountDiscountPolicyConfigEntity policyEntity = createAmountDiscountPolicyConfigEntity();
        AmountDiscountPolicyConfig expectedConfig = createAmountDiscountPolicyConfig();
        given(amountDiscountPolicyConfigRepositoryMock.findById(DEFAULT_POLICY)).willReturn(Optional.of(policyEntity));
        given(amountDiscountPolicyConfigMapperMock.toDto(policyEntity)).willReturn(expectedConfig);

        //when
        AmountDiscountPolicyConfig policyConfig = amountDiscountPolicyService.get();

        //then
        assertThat(policyConfig).isEqualTo(expectedConfig);
        then(amountDiscountPolicyConfigRepositoryMock).should().findById(DEFAULT_POLICY);
        then(amountDiscountPolicyConfigMapperMock).should().toDto(policyEntity);
    }

    @Test
    void shouldUpdateExisting() {
        //given
        AmountDiscountPolicyConfigEntity policyEntity = createAmountDiscountPolicyConfigEntity();
        AmountDiscountPolicyConfig config = createAmountDiscountPolicyConfig();
        given(amountDiscountPolicyConfigMapperMock.toEntity(config)).willReturn(policyEntity);

        //when
        amountDiscountPolicyService.upsert(config);

        //then
        then(amountDiscountPolicyConfigMapperMock).should().toEntity(config);
        then(amountDiscountPolicyConfigRepositoryMock).should().deleteById(DEFAULT_POLICY);
        then(amountDiscountPolicyConfigRepositoryMock).should().save(policyEntity);
    }

    @Test
    void shouldProvideDiscountPolicy() {
        //given
        AmountDiscountPolicyConfigEntity policyEntity = createAmountDiscountPolicyConfigEntity();
        AmountDiscountPolicyConfig expectedConfig = createAmountDiscountPolicyConfig();
        given(amountDiscountPolicyConfigRepositoryMock.findById(DEFAULT_POLICY)).willReturn(Optional.of(policyEntity));
        given(amountDiscountPolicyConfigMapperMock.toDto(policyEntity)).willReturn(expectedConfig);

        //when
        DiscountPolicy provided = amountDiscountPolicyService.provide();

        //then
        assertThat(provided).isExactlyInstanceOf(AmountBasedDiscountPolicy.class);
    }

    private static AmountDiscountPolicyConfig createAmountDiscountPolicyConfig() {
        return AmountDiscountPolicyConfig.builder()
                .build();
    }

    private static AmountDiscountPolicyConfigEntity createAmountDiscountPolicyConfigEntity() {
        return AmountDiscountPolicyConfigEntity.builder()
                .build();
    }
}