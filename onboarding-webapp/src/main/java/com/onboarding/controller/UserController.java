package com.onboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserClient userClient;

	@GetMapping
	public List<UserDto> findAll() {
		return userClient.findAllUsers();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto create(@RequestBody UserDto dto) {
		return userClient.create(dto);
	}

}
