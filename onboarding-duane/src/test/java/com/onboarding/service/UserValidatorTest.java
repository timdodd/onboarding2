package com.onboarding.service;

import com.onboarding.api.UserDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class UserValidatorTest {


	@Test
	public void testValidate_whenFirstNameNull_shouldCreateError() {
		UserValidator userValidator = new UserValidator();
		UserDto dto = getValidUser().setFirstName(null);
		Map<String, String> errors = userValidator.validate(dto);

		assertTrue(errors.size() == 1);
		assertTrue(errors.containsKey("firstName"));
		assertEquals(UserValidator.FIRST_NAME_REQUIRED, errors.get("firstName"));
	}

	//... more things.


	public UserDto getValidUser() {
		return new UserDto()
				.setFirstName("Tim")
				.setLastName("Dodd")
				.setUsername("doddt2");
	}

}
