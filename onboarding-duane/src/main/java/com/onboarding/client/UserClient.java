package com.onboarding.client;

import com.onboarding.api.PhoneDto;
import com.onboarding.api.UserDto;
import com.onboarding.entity.Phone;
import lombok.Setter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.List;
import java.util.UUID;

public class UserClient {

	private Client client = ClientBuilder.newClient();

	@Setter
	private String baseUri;

	public UserDto create(UserDto dto) {
		return userTarget()
				.request()
				.post(Entity.json(dto), UserDto.class);
	}

	public UserDto update(UserDto dto) {
		return userTarget()
				.path(dto.getUserId().toString())
				.request()
				.put(Entity.json(dto), UserDto.class);
	}

	public void delete(UUID userId) {
		userTarget()
				.path(userId.toString())
				.request()
				.delete(Void.class);
	}

	public UserDto get(UUID userId) {
		return userTarget()
				.path(userId.toString())
				.request()
				.get(UserDto.class);
	}

	public List<UserDto> findAllUsers() {
		return userTarget()
				.request()
				.get(new GenericType<List<UserDto>>() {
				});
	}

	//added Phone Methods
	public PhoneDto create(UUID userId, PhoneDto phoneDto) {
		return phoneTarget(userId)
				.request()
				.post(Entity.json(phoneDto), PhoneDto.class);
	}

	public PhoneDto update(UserDto userDto, PhoneDto phoneDto) {
		return phoneTarget(userDto.getUserId(), phoneDto.getPhoneId())
				.request()
				.put(Entity.json(phoneDto), PhoneDto.class);
	}

	public PhoneDto updatePrimaryPhoneNumber(UUID userId, PhoneDto phoneDto) {//working on this
		return phoneTarget(userId, phoneDto.getPhoneId())
				.request()
				.put(Entity.json(phoneDto), PhoneDto.class);
	}

	public void delete(UUID userId, UUID phoneId) {
		phoneTarget(userId, phoneId)
				.request()
				.delete(Void.class);
	}

	public List<PhoneDto> findAllPhones(UUID userId) {
		return phoneTarget(userId)
				.request()
				.get(new GenericType<List<PhoneDto>>() {
				});
	}

	public PhoneDto get(UUID userId, UUID phoneId) {
		return phoneTarget(userId, phoneId)
				.request()
				.get(PhoneDto.class);
	}



	//targets all phones of a user
	private WebTarget phoneTarget(UUID userId) {
		return userTarget(userId)
				.path("phones");
	}

	//targets a specific user phone
	private WebTarget phoneTarget(UUID userId, UUID phoneId) {
		return userTarget(userId)
				.path("phones")
				.path(phoneId.toString());
	}

	//targets a specific user
	private WebTarget userTarget(UUID userId) {
		return userTarget()
				.path(userId.toString());
	}

	//targets all users
	private WebTarget userTarget() {
		return client.target(baseUri)
				.path("api")
				.path("v1")
				.path("users");
	}
}
