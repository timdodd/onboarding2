package com.onboarding.service;

import com.onboarding.api.UserDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.onboarding.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.validation.constraints.AssertTrue;
import java.util.Map;

public class UserValidatorTest {

	@Test
	public void testValidate_whenFirstNameNull_shouldCreateError() {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		UserValidator userValidator = new UserValidator(userRepository);
		UserDto dto = getValidUser().setFirstName(null);
		Map<String, String> errors = userValidator.validate(dto);

		assertTrue(errors.size() == 1);
		assertTrue(errors.containsKey("firstName"));
		assertEquals(UserValidator.FIRST_NAME_REQUIRED, errors.get("firstName"));
	}

	//... more things.

	//added
	@Test
	public void testValidate_whenUsernameExists_shouldCreateError(){
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		UserValidator userValidator = new UserValidator(userRepository);
		UserDto dto = getValidUser();
		Mockito.when(userRepository.existsUserByUsername(dto.getUsername())).thenReturn(true);
		Map<String, String> errors = userValidator.validate(dto);

		assertTrue(errors.size() == 1);
		assertTrue(errors.containsKey("username"));
		assertEquals(UserValidator.USERNAME_TAKEN, errors.get("username"));

	}

	public UserDto getValidUser() {
		return new UserDto()
				.setFirstName("Tim")
				.setLastName("Dodd")
				.setUsername("doddt2");
	}

}
