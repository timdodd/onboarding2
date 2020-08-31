package com.onboarding.exception;

import lombok.Getter;

import java.util.Map;

public class ValidationException extends RuntimeException {

	@Getter
	private Map<String, String> errors;

	public ValidationException(Map<String, String> errors) {
		this.errors = errors;
	}
}
