package com.tryton.price_calculator.service.discount.selector;

import com.tryton.price_calculator.model.AppliedDiscountPolicyConfig;
import com.tryton.price_calculator.service.discount.AppliedDiscountPolicyService;
import com.tryton.price_calculator.service.discount.DiscountPolicy;
import com.tryton.price_calculator.service.discount.DiscountPolicyProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ConfigBasedDiscountPolicySelectorTest {

    private static final String DEFAULT_POLICY_NAME = "defaultPolicyName";

    @Mock
    private DiscountPolicy discountPolicyMock;

    @Mock
    private DiscountPolicyProvider percentageBasedDiscountPolicyProviderMock;
    @Mock
    private DiscountPolicyProvider amountBasedDiscountPolicyProviderMock;
    @Mock
    private DiscountPolicyProvider noDiscountPolicyProviderMock;
    @Mock
    private AppliedDiscountPolicyService appliedDiscountPolicyServiceMock;

    private ConfigBasedDiscountPolicySelector configBasedDiscountPolicySelector;

    @BeforeEach
    void setUp() {
        configBasedDiscountPolicySelector = new ConfigBasedDiscountPolicySelector(DEFAULT_POLICY_NAME,
                percentageBasedDiscountPolicyProviderMock, amountBasedDiscountPolicyProviderMock,
                noDiscountPolicyProviderMock, appliedDiscountPolicyServiceMock);
    }

    @Test
    void shouldSelectNoDiscountPolicyWhenAppliedDiscountPolicyServiceFoundNothing() {
        //given
        given(noDiscountPolicyProviderMock.provide()).willReturn(discountPolicyMock);

        //when
        DiscountPolicy selected = configBasedDiscountPolicySelector.select();

        //then
        assertThat(selected).isEqualTo(discountPolicyMock);
        then(noDiscountPolicyProviderMock).should().provide();
        then(appliedDiscountPolicyServiceMock).should().get();
        then(percentageBasedDiscountPolicyProviderMock).shouldHaveNoInteractions();
        then(amountBasedDiscountPolicyProviderMock).shouldHaveNoInteractions();
    }

    @Test
    void shouldSelectPercentageDiscountPolicyWhenAppliedDiscountPolicyServiceFoundPercentage() {
        //given
        AppliedDiscountPolicyConfig config = createAppliedDiscountPolicyConfig("percentage");
        given(appliedDiscountPolicyServiceMock.get()).willReturn(config);
        given(percentageBasedDiscountPolicyProviderMock.provide()).willReturn(discountPolicyMock);

        //when
        DiscountPolicy selected = configBasedDiscountPolicySelector.select();

        //then
        assertThat(selected).isEqualTo(discountPolicyMock);
        then(percentageBasedDiscountPolicyProviderMock).should().provide();
        then(appliedDiscountPolicyServiceMock).should().get();
        then(noDiscountPolicyProviderMock).shouldHaveNoInteractions();
        then(amountBasedDiscountPolicyProviderMock).shouldHaveNoInteractions();
    }

    @Test
    void shouldSelectAmountDiscountPolicyWhenAppliedDiscountPolicyServiceFoundAmount() {
        //given
        AppliedDiscountPolicyConfig config = createAppliedDiscountPolicyConfig("amount");
        given(appliedDiscountPolicyServiceMock.get()).willReturn(config);
        given(amountBasedDiscountPolicyProviderMock.provide()).willReturn(discountPolicyMock);

        //when
        DiscountPolicy selected = configBasedDiscountPolicySelector.select();

        //then
        assertThat(selected).isEqualTo(discountPolicyMock);
        then(amountBasedDiscountPolicyProviderMock).should().provide();
        then(appliedDiscountPolicyServiceMock).should().get();
        then(noDiscountPolicyProviderMock).shouldHaveNoInteractions();
        then(percentageBasedDiscountPolicyProviderMock).shouldHaveNoInteractions();
    }

    private static AppliedDiscountPolicyConfig createAppliedDiscountPolicyConfig(String policyName) {
        return AppliedDiscountPolicyConfig.builder()
                .policyName(policyName)
                .build();
    }
}
