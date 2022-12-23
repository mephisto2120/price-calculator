package com.tryton.price_calculator.service.discount;

import com.tryton.price_calculator.domain.mongo.AppliedDiscountPolicyConfigEntity;
import com.tryton.price_calculator.exception.handler.PolicyNotFoundException;
import com.tryton.price_calculator.mapper.AppliedDiscountPolicyConfigMapper;
import com.tryton.price_calculator.model.AppliedDiscountPolicyConfig;
import com.tryton.price_calculator.repository.mongo.AppliedDiscountPolicyConfigRepository;
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
class AppliedDiscountPolicyServiceTest {
    private static final String DEFAULT_POLICY = "default-policy";

    @Mock
    private AppliedDiscountPolicyConfigRepository appliedDiscountPolicyConfigRepositoryMock;
    @Mock
    private AppliedDiscountPolicyConfigMapper appliedDiscountPolicyConfigMapperMock;

    @InjectMocks
    private AppliedDiscountPolicyService appliedDiscountPolicyService;


    @Test
    void shouldThrowExceptionWhenPolicyNotFound() {
        //given
        given(appliedDiscountPolicyConfigRepositoryMock.findById(DEFAULT_POLICY)).willReturn(Optional.empty());

        //when
        Throwable thrown = catchThrowable(() -> appliedDiscountPolicyService.get());

        //then
        assertThat(thrown)
                .isInstanceOf(PolicyNotFoundException.class)
                .hasMessage("Policy default-policy was not found");
        then(appliedDiscountPolicyConfigRepositoryMock).should().findById(DEFAULT_POLICY);
        then(appliedDiscountPolicyConfigMapperMock).shouldHaveNoInteractions();
    }

    @Test
    void shouldGetPolicyWhenFoundInDatabase() {
        //given
        AppliedDiscountPolicyConfigEntity policyEntity = createAppliedDiscountPolicyConfigEntity();
        AppliedDiscountPolicyConfig expectedConfig = createAppliedDiscountPolicyConfig();
        given(appliedDiscountPolicyConfigRepositoryMock.findById(DEFAULT_POLICY)).willReturn(Optional.of(policyEntity));
        given(appliedDiscountPolicyConfigMapperMock.toDto(policyEntity)).willReturn(expectedConfig);

        //when
        AppliedDiscountPolicyConfig policyConfig = appliedDiscountPolicyService.get();

        //then
        assertThat(policyConfig).isEqualTo(expectedConfig);
        then(appliedDiscountPolicyConfigRepositoryMock).should().findById(DEFAULT_POLICY);
        then(appliedDiscountPolicyConfigMapperMock).should().toDto(policyEntity);
    }

    @Test
    void shouldUpdateExisting() {
        //given
        AppliedDiscountPolicyConfigEntity policyEntity = createAppliedDiscountPolicyConfigEntity();
        AppliedDiscountPolicyConfig config = createAppliedDiscountPolicyConfig();
        given(appliedDiscountPolicyConfigMapperMock.toEntity(config)).willReturn(policyEntity);

        //when
        appliedDiscountPolicyService.upsert(config);

        //then
        then(appliedDiscountPolicyConfigMapperMock).should().toEntity(config);
        then(appliedDiscountPolicyConfigRepositoryMock).should().deleteById(DEFAULT_POLICY);
        then(appliedDiscountPolicyConfigRepositoryMock).should().save(policyEntity);
    }

    private static AppliedDiscountPolicyConfig createAppliedDiscountPolicyConfig() {
        return AppliedDiscountPolicyConfig.builder()
                .build();
    }

    private static AppliedDiscountPolicyConfigEntity createAppliedDiscountPolicyConfigEntity() {
        return AppliedDiscountPolicyConfigEntity.builder()
                .build();
    }
}