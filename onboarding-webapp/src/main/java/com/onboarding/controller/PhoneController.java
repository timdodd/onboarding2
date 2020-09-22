package com.onboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{userId}/phones")
public class PhoneController {

    @Autowired
    private UserClient userClient;

    @GetMapping
    public List<PhoneDto> getAllPhones(@PathVariable("userId") UUID userId) {
        return userClient.getAllPhones(userId);
    }

    @GetMapping("/{phoneId}")
    public PhoneDto getUserPhone(@PathVariable("userId") UUID userId, @PathVariable("phoneId") UUID phoneId) {
        return userClient.getUserPhone(userId,phoneId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PhoneDto createPhone(@PathVariable("userId") UUID userId, @RequestBody PhoneDto dto) {
        dto.setUserId(userId);
        return userClient.createPhone(userId, dto);
    }

    @DeleteMapping("/{phoneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePhone(@PathVariable("userId") UUID userId, @PathVariable("phoneId") UUID phoneId) {
        userClient.deletePhone(userId, phoneId);
    }

    @PutMapping("/{phoneId}")
    public PhoneDto updatePhone(@PathVariable("userId") UUID userId, @PathVariable("phoneId") UUID phoneId, @RequestBody PhoneDto dto) {
        dto.setUserId(userId);
        dto.setPhoneId(phoneId);
        return userClient.updatePhone(userId, dto);
    }

    @PostMapping("/{phoneId}/sendVerify")
    public void sendVerify (@PathVariable("userId") UUID userId, @RequestBody PhoneDto dto) {
        userClient.sendVerify(userId, dto);
    }

    @PutMapping("/{phoneId}/verify/{code}")
    public void checkVerify (@PathVariable("userId") UUID userId, @PathVariable("code") String code, @RequestBody PhoneDto dto) {
        userClient.checkVerify(userId, dto, code);
    }
}
