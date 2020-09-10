package com.onboarding.controller;

import com.onboarding.api.UserDto;
import com.onboarding.client.UserClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import javax.ws.rs.NotFoundException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = "classpath:clean-up.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserControllerTest {

	@LocalServerPort
	private int port;

	private UserClient userClient;

	@BeforeEach
	public void init() {
		userClient = new UserClient();
		userClient.setBaseUri("http://localhost:" + port);
	}

	@Test
	public void testCreate_whenValid_shouldCreateUser() {
		UserDto createUser = getValidUser();
		UserDto responseUser = userClient.create(createUser);
		assertNotNull(responseUser.getUserId());
	}

	@Test
	public void testCreateAndDelete_whenValid_shouldDeleteUser() {
		UUID createdUserId = createUser("doddt");
		assertEquals(1, countUsers());
		userClient.delete(createdUserId);
		assertEquals(0, countUsers());
	}

	@Test
	public void testGet_whenNotFound_shouldThrowNotFound() {
		assertThrows(NotFoundException.class, () -> {
			userClient.get(new UUID(0, 1));
		});
	}


	private int countUsers() {
		return userClient.findAllUsers().size();
	}

	private UUID createUser(String userName) {
		UserDto dto = new UserDto()
				.setFirstName(userName)
				.setLastName(userName)
				.setUsername(userName);
		return userClient.create(dto).getUserId();
	}


	public UserDto getValidUser() {
		return new UserDto()
				.setFirstName("Tim")
				.setLastName("Dodd")
				.setUsername("doddt2");
	}


}
