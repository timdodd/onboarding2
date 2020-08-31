package com.onboarding.controller;

import com.onboarding.api.UserDto;
import com.onboarding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping
	public List<UserDto> findAll() {
		return userService.findAll();
	}

	@GetMapping(params = "lastName")
	public List<UserDto> findAllByLastName(@RequestParam("lastName") String lastName) {
		return userService.findAllByLastName(lastName);
	}

	@GetMapping("/{userId}")
	public UserDto get(@PathVariable("userId") UUID userId) {
		return userService.get(userId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto create(@RequestBody UserDto dto) {
		return userService.create(dto);
	}

	@PutMapping("/{userId}")
	public UserDto update(@PathVariable("userId") UUID userId, @RequestBody UserDto dto) {
		dto.setUserId(userId);
		return userService.update(dto);
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("userId") UUID userId) {
		userService.delete(userId);
	}
}
