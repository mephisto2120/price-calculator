package com.tryton.price_calculator.controller;

import com.tryton.price_calculator.model.AmountDiscountPolicyConfig;
import com.tryton.price_calculator.model.AppliedDiscountPolicyConfig;
import com.tryton.price_calculator.model.PercentageDiscountPolicyConfig;
import com.tryton.price_calculator.service.discount.AmountDiscountPolicyService;
import com.tryton.price_calculator.service.discount.AppliedDiscountPolicyService;
import com.tryton.price_calculator.service.discount.PercentageDiscountPolicyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DiscountPolicyControllerTest {

    @Mock
    private PercentageDiscountPolicyService percentageDiscountPolicyServiceMock;
    @Mock
    private AmountDiscountPolicyService amountDiscountPolicyServiceMock;
    @Mock
    private AppliedDiscountPolicyService appliedDiscountPolicyServiceMock;

    @InjectMocks
    private DiscountPolicyController discountPolicyController;

    @Test
    void getPercentageDiscountPolicy() {
        //given
        PercentageDiscountPolicyConfig policyConfig = PercentageDiscountPolicyConfig.builder()
                .build();
        given(percentageDiscountPolicyServiceMock.get()).willReturn(policyConfig);

        //when
        PercentageDiscountPolicyConfig percentageDiscountPolicy = discountPolicyController.getPercentageDiscountPolicy();

        //then
        assertThat(percentageDiscountPolicy).isEqualTo(policyConfig);
        then(percentageDiscountPolicyServiceMock).should().get();
        then(amountDiscountPolicyServiceMock).shouldHaveNoInteractions();
        then(appliedDiscountPolicyServiceMock).shouldHaveNoInteractions();
    }

    @Test
    void updatePercentageDiscountPolicy() {
        //given
        PercentageDiscountPolicyConfig policyConfig = PercentageDiscountPolicyConfig.builder()
                .build();

        //when
        discountPolicyController.updatePercentageDiscountPolicy(policyConfig);

        //then
        then(percentageDiscountPolicyServiceMock).should().upsert(policyConfig);
        then(amountDiscountPolicyServiceMock).shouldHaveNoInteractions();
        then(appliedDiscountPolicyServiceMock).shouldHaveNoInteractions();
    }

    @Test
    void getAmountDiscountPolicy() {
        //given
        AmountDiscountPolicyConfig policyConfig = AmountDiscountPolicyConfig.builder()
                .build();
        given(amountDiscountPolicyServiceMock.get()).willReturn(policyConfig);

        //when
        AmountDiscountPolicyConfig amountDiscountPolicy = discountPolicyController.getAmountDiscountPolicy();

        //then
        assertThat(amountDiscountPolicy).isEqualTo(policyConfig);
        then(amountDiscountPolicyServiceMock).should().get();
        then(percentageDiscountPolicyServiceMock).shouldHaveNoInteractions();
        then(appliedDiscountPolicyServiceMock).shouldHaveNoInteractions();
    }

    @Test
    void updateAmountDiscountPolicy() {
        //given
        AmountDiscountPolicyConfig policyConfig = AmountDiscountPolicyConfig.builder()
                .build();

        //when
        discountPolicyController.updateAmmountDiscountPolicy(policyConfig);

        //then
        then(amountDiscountPolicyServiceMock).should().upsert(policyConfig);
        then(percentageDiscountPolicyServiceMock).shouldHaveNoInteractions();
        then(appliedDiscountPolicyServiceMock).shouldHaveNoInteractions();
    }

    @Test
    void getAppliedDiscountPolicy() {
        //given
        AppliedDiscountPolicyConfig policyConfig = AppliedDiscountPolicyConfig.builder()
                .build();
        given(appliedDiscountPolicyServiceMock.get()).willReturn(policyConfig);

        //when
        AppliedDiscountPolicyConfig appliedDiscountPolicy = discountPolicyController.getAppliedDiscountPolicy();

        //then
        assertThat(appliedDiscountPolicy).isEqualTo(policyConfig);
        then(appliedDiscountPolicyServiceMock).should().get();
        then(percentageDiscountPolicyServiceMock).shouldHaveNoInteractions();
        then(amountDiscountPolicyServiceMock).shouldHaveNoInteractions();
    }

    @Test
    void updateAppliedDiscountPolicy() {
        //given
        AppliedDiscountPolicyConfig policyConfig = AppliedDiscountPolicyConfig.builder()
                .build();

        //when
        discountPolicyController.updateAppliedDiscountPolicy(policyConfig);

        //then
        then(appliedDiscountPolicyServiceMock).should().upsert(policyConfig);
        then(percentageDiscountPolicyServiceMock).shouldHaveNoInteractions();
        then(amountDiscountPolicyServiceMock).shouldHaveNoInteractions();
    }
}
