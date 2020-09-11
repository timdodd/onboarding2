package com.onboarding.service;

import com.onboarding.api.UserDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.onboarding.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Map;
import java.util.UUID;

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

	@Test
	public void testValidate_whenLastNameNull_shouldCreateError() {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		UserValidator userValidator = new UserValidator(userRepository);
		UserDto dto = getValidUser().setLastName(null);
		Map<String, String> errors = userValidator.validate(dto);

		assertTrue(errors.size() == 1);
		assertTrue(errors.containsKey("lastName"));
		assertEquals(UserValidator.LAST_NAME_REQUIRED, errors.get("lastName"));
	}

	@Test
	public void testValidate_whenUsernameNull_shouldCreateError() {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		UserValidator userValidator = new UserValidator(userRepository);
		UserDto dto = getValidUser().setUsername(null);
		Map<String, String> errors = userValidator.validate(dto);

		assertTrue(errors.size() == 1);
		assertTrue(errors.containsKey("username"));
		assertEquals(UserValidator.USERNAME_REQUIRED, errors.get("username"));
	}

	@Test
	public void testValidate_whenFirstNameGreaterThanFifty_shouldCreateError() {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		UserValidator userValidator = new UserValidator(userRepository);
		String str = "11111111111111111111111111111111111111111111111111111111111111";
		UserDto dto = getValidUser().setFirstName(str);
		Map<String, String> errors = userValidator.validate(dto);

		assertTrue(errors.size() == 1);
		assertTrue(errors.containsKey("firstName"));
		assertEquals(UserValidator.FIRST_NAME_LT_50, errors.get("firstName"));
	}

	@Test
	public void testValidate_whenLastNameGreaterThanFifty_shouldCreateError() {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		UserValidator userValidator = new UserValidator(userRepository);
		String str = "11111111111111111111111111111111111111111111111111111111111111";
		UserDto dto = getValidUser().setLastName(str);
		Map<String, String> errors = userValidator.validate(dto);

		assertTrue(errors.size() == 1);
		assertTrue(errors.containsKey("lastName"));
		assertEquals(UserValidator.LAST_NAME_LT_50, errors.get("lastName"));
	}

	@Test
	public void testValidate_whenUsernameGreaterThanFifty_shouldCreateError() {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		UserValidator userValidator = new UserValidator(userRepository);
		String str = "11111111111111111111111111111111111111111111111111111111111111";
		UserDto dto = getValidUser().setUsername(str);
		Map<String, String> errors = userValidator.validate(dto);

		assertTrue(errors.size() == 1);
		assertTrue(errors.containsKey("username"));
		assertEquals(UserValidator.USERNAME_LT_50, errors.get("username"));
	}

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
				.setUserId(new UUID(0,1))
				.setFirstName("Tim")
				.setLastName("Dodd")
				.setUsername("doddt2");
	}

}
