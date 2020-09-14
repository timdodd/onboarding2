package com.onboarding.controller;

import com.onboarding.api.PhoneDto;
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
public class PhoneControllerTest {

    @LocalServerPort
    private int port;

    private UserClient userClient;
    @BeforeEach
    public void init() {
        userClient = new UserClient();
        userClient.setBaseUri("http://localhost:" + port);
    }

    @Test
    public void testCreate_whenValid_shouldCreatePhone() {
        UserDto createUser = getValidUser();
        UserDto createdUser = userClient.create(createUser);
        assertNotNull(createdUser.getUserId());
        PhoneDto createdPhone = getValidUserPhone();
        PhoneDto responsePhone = userClient.create(createdUser.getUserId(), createdPhone);
        assertNotNull(responsePhone.getPhoneId());
    }
    
    @Test
    public void testCreateAndDelete_whenValid_shouldDeletePhone() {
        UserDto createUser = getValidUser();
        UserDto createdUser = userClient.create(createUser);
        assertNotNull(createdUser.getUserId());
        PhoneDto createdPhone = getValidUserPhone();
        PhoneDto responsePhone = userClient.create(createdUser.getUserId(), createdPhone);
        assertEquals(1, countPhones(createdUser.getUserId()));
        userClient.delete(createdUser.getUserId(), responsePhone.getPhoneId());
        assertEquals(0, countPhones(createdUser.getUserId()));
    }

    @Test
    public void testGet_whenNotFound_shouldThrowNotFound() {
        assertThrows(NotFoundException.class, () -> {
            userClient.get(new UUID(0, 1), new UUID(0, 2));
        });
    }

    @Test
    public void testCreateAndUpdate_whenValid_shouldUpdatePhone() {
        UserDto createUser = getValidUser();
        UserDto createdUser = userClient.create(createUser);
        assertNotNull(createdUser.getUserId());
        PhoneDto createdPhone = getValidUserPhone();
        PhoneDto responsePhone = userClient.create(createdUser.getUserId(), createdPhone);
        assertEquals(1, countPhones(createdUser.getUserId()));
        createdPhone.setUserId(createdUser.getUserId());
        responsePhone.setPhoneName("testName");
        responsePhone.setPhoneNumber("01234567891");
        userClient.update(createdUser, responsePhone);
        assertEquals("testName", userClient.get(createdUser.getUserId(), responsePhone.getPhoneId()).getPhoneName());
        assertEquals("01234567891", userClient.get(createdUser.getUserId(), responsePhone.getPhoneId()).getPhoneNumber());
    }

    private int countPhones(UUID userId) {
        return userClient.findAllPhones(userId).size();
    }

    public UserDto getValidUser() {
        return new UserDto()
                .setUserId(new UUID(0,1))
                .setFirstName("Duane")
                .setLastName("Classen")
                .setUsername("classend");
    }
    
    public PhoneDto getValidUserPhone() {
        return new PhoneDto()
                .setUserId(new UUID(0,1))
                .setPhoneId(new UUID(0,2))
                .setPhoneName("Duane's Phone")
                .setPhoneNumber("13062316467")
                .setPrimaryPhoneNumber(true)
                .setPhoneNumberVerified(false);
    }
}
