package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.domain.mongo.PercentageDiscountPolicyConfigEntity;
import com.tryton.price_calculator.exception.handler.PolicyNotFoundException;
import com.tryton.price_calculator.mapper.PercentageDiscountPolicyConfigMapper;
import com.tryton.price_calculator.model.PercentageDiscountPolicyConfig;
import com.tryton.price_calculator.repository.mongo.PercentageDiscountPolicyConfigRepository;
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
class PercentageDiscountPolicyServiceTest {
    private static final String DEFAULT_POLICY = "default-policy";

    @Mock
    private PercentageDiscountPolicyConfigRepository percentageDiscountPolicyConfigRepositoryMock;
    @Mock
    private PercentageDiscountPolicyConfigMapper percentageDiscountPolicyConfigMapperMock;

    @InjectMocks
    private PercentageDiscountPolicyService percentageDiscountPolicyService;


    @Test
    void shouldThrowExceptionWhenPolicyNotFound() {
        //given
        given(percentageDiscountPolicyConfigRepositoryMock.findById(DEFAULT_POLICY)).willReturn(Optional.empty());

        //when
        Throwable thrown = catchThrowable(() -> percentageDiscountPolicyService.get());

        //then
        assertThat(thrown)
                .isInstanceOf(PolicyNotFoundException.class)
                .hasMessage("Policy default-policy was not found");
        then(percentageDiscountPolicyConfigRepositoryMock).should().findById(DEFAULT_POLICY);
        then(percentageDiscountPolicyConfigMapperMock).shouldHaveNoInteractions();
    }

    @Test
    void shouldGetPolicyWhenFoundInDatabase() {
        //given
        PercentageDiscountPolicyConfigEntity policyEntity = createPercentageDiscountPolicyConfigEntity();
        PercentageDiscountPolicyConfig expectedConfig = createPercentageDiscountPolicyConfig();
        given(percentageDiscountPolicyConfigRepositoryMock.findById(DEFAULT_POLICY)).willReturn(Optional.of(policyEntity));
        given(percentageDiscountPolicyConfigMapperMock.toDto(policyEntity)).willReturn(expectedConfig);

        //when
        PercentageDiscountPolicyConfig policyConfig = percentageDiscountPolicyService.get();

        //then
        assertThat(policyConfig).isEqualTo(expectedConfig);
        then(percentageDiscountPolicyConfigRepositoryMock).should().findById(DEFAULT_POLICY);
        then(percentageDiscountPolicyConfigMapperMock).should().toDto(policyEntity);
    }

    @Test
    void shouldUpdateExisting() {
        //given
        PercentageDiscountPolicyConfigEntity policyEntity = createPercentageDiscountPolicyConfigEntity();
        PercentageDiscountPolicyConfig config = createPercentageDiscountPolicyConfig();
        given(percentageDiscountPolicyConfigMapperMock.toEntity(config)).willReturn(policyEntity);

        //when
        percentageDiscountPolicyService.upsert(config);

        //then
        then(percentageDiscountPolicyConfigMapperMock).should().toEntity(config);
        then(percentageDiscountPolicyConfigRepositoryMock).should().deleteById(DEFAULT_POLICY);
        then(percentageDiscountPolicyConfigRepositoryMock).should().save(policyEntity);
    }

    @Test
    void shouldProvideDiscountPolicy() {
        //given
        PercentageDiscountPolicyConfigEntity policyEntity = createPercentageDiscountPolicyConfigEntity();
        PercentageDiscountPolicyConfig expectedConfig = createPercentageDiscountPolicyConfig();
        given(percentageDiscountPolicyConfigRepositoryMock.findById(DEFAULT_POLICY)).willReturn(Optional.of(policyEntity));
        given(percentageDiscountPolicyConfigMapperMock.toDto(policyEntity)).willReturn(expectedConfig);

        //when
        DiscountPolicy provided = percentageDiscountPolicyService.provide();

        //then
        assertThat(provided).isExactlyInstanceOf(PercentageBasedDiscountPolicy.class);
    }

    private static PercentageDiscountPolicyConfig createPercentageDiscountPolicyConfig() {
        return PercentageDiscountPolicyConfig.builder()
                .build();
    }

    private static PercentageDiscountPolicyConfigEntity createPercentageDiscountPolicyConfigEntity() {
        return PercentageDiscountPolicyConfigEntity.builder()
                .build();
    }
}
