package com.onboarding.service;

import com.onboarding.api.UserDto;
import com.onboarding.controller.UserController;
import com.onboarding.exception.ValidationException;
import com.onboarding.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UserValidator {

	static String FIRST_NAME_REQUIRED = "FIRST_NAME_REQUIRED";
	static String LAST_NAME_REQUIRED = "LAST_NAME_REQUIRED";
	static String FIRST_NAME_LT_50 = "FIRST_NAME_LT_50";
	static String LAST_NAME_LT_50 = "LAST_NAME_LT_50";
	static String USERNAME_REQUIRED = "USERNAME_REQUIRED";
	static String USERNAME_TAKEN = "USERNAME_TAKEN";

	private UserRepository userRepository;

	UserValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void validateAndThrow(UserDto dto) {
		Map<String, String> errors = validate(dto);
		if (!errors.isEmpty()) {
			throw new ValidationException(errors);
		}
	}

	public Map<String, String> validate(UserDto dto) {
		Map<String, String> errors = new LinkedHashMap<>();

		if (StringUtils.isBlank(dto.getLastName())) {
			errors.put("lastName", LAST_NAME_REQUIRED);
		} else if(dto.getLastName().length() > 50) {
			errors.put("lastName", LAST_NAME_LT_50);
		}

		if (StringUtils.isBlank(dto.getFirstName())) {
			errors.put("firstName", FIRST_NAME_REQUIRED);
		} else if(dto.getLastName().length() > 50) {
			errors.put("firstName", FIRST_NAME_LT_50);
		}

		if (StringUtils.isBlank(dto.getUsername())) {
			errors.put("username", USERNAME_REQUIRED);
		}

//		if(userRepository.existsUserByUsername(dto.getUsername())) {
//			errors.put("username", USERNAME_TAKEN);
//		}

		return errors;
	}
}
