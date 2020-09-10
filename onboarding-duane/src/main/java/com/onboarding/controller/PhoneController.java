package com.onboarding.controller;

import com.onboarding.api.PhoneDto;
import com.onboarding.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/users/{userid}/phones")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping
    public List<PhoneDto> findAll() {
        return phoneService.findAllPhones();
    }

    @GetMapping("/{phoneId}")
    public PhoneDto get(@PathVariable("phoneId") UUID phoneId) {
        return phoneService.get(phoneId);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PhoneDto create(@PathVariable("userid") UUID userId, @RequestBody PhoneDto dto) {
        dto.setUserId(userId);
        return phoneService.create(dto);
    }

    @PutMapping("/{phoneId}")
    public PhoneDto update(@PathVariable("userid") UUID userId, @PathVariable("phoneId") UUID phoneId, @RequestBody PhoneDto dto) {
        dto.setUserId(userId);
        dto.setPhoneId(phoneId);
        return phoneService.update(dto);
    }

    
    @DeleteMapping("/{phoneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("phoneId") UUID phoneId) {
        phoneService.delete(phoneId);
    }
}
