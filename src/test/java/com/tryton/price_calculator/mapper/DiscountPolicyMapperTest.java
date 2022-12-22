package com.tryton.price_calculator.mapper;

import com.tryton.price_calculator.domain.mongo.DiscountPolicyEntity;
import com.tryton.price_calculator.model.DiscountPolicy;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicyMapperTest {

	private static final String USER = "HikaruAppUser";
	private static final String NAME = "Hikaru";

	public static final LocalDateTime CREATED_AT = LocalDateTime.of(2022, 12, 5, 12, 20, 30);
	private static final String CREATED_AT_STR = "2022-12-05T12:20:30";

	private final DiscountPolicyMapper discountPolicyMapper = Mappers.getMapper(DiscountPolicyMapper.class);

	@Test
	void shouldMapToNullIfNullGiven() {
		//given
		DiscountPolicy discountPolicy = null;

		//when
		DiscountPolicyEntity discountPolicyEntity = discountPolicyMapper.toEntity(discountPolicy);

		//then
		assertThat(discountPolicyEntity).isNull();
	}

	@Test
	void shouldMapToEntityWhenDatesNotFilled() {
		//given
		DiscountPolicy discountPolicy = createComplaint()
				.build();

		//when
		DiscountPolicyEntity discountPolicyEntity = discountPolicyMapper.toEntity(discountPolicy);

		//then
		assertCommonFields(discountPolicyEntity);
		assertThat(discountPolicyEntity.getCreatedAt()).isNull();
		assertThat(discountPolicyEntity.getModifiedAt()).isNull();
	}

	@Test
	void shouldMapToEntity() {
		//given
		DiscountPolicy discountPolicy = createComplaint()
				.createdAt(CREATED_AT)
				.modifiedAt(CREATED_AT)
				.build();

		//when
		DiscountPolicyEntity discountPolicyEntity = discountPolicyMapper.toEntity(discountPolicy);

		//then
		assertCommonFields(discountPolicyEntity);
		assertThat(discountPolicyEntity.getCreatedAt()).isEqualTo(CREATED_AT_STR);
		assertThat(discountPolicyEntity.getModifiedAt()).isEqualTo(CREATED_AT_STR);
	}

	private static DiscountPolicy.DiscountPolicyBuilder<?, ?> createComplaint() {
		return DiscountPolicy.builder()
				.name(NAME)
				.createdBy(USER)
				.modifiedBy(USER);
	}

	private static void assertCommonFields(DiscountPolicyEntity discountPolicyEntity) {
		assertThat(discountPolicyEntity).isNotNull();
		assertThat(discountPolicyEntity.getId()).isNotNull();
		assertThat(discountPolicyEntity.getName()).isEqualTo(NAME);
		assertThat(discountPolicyEntity.getCreatedBy()).isEqualTo(USER);
		assertThat(discountPolicyEntity.getModifiedBy()).isEqualTo(USER);
	}
}
