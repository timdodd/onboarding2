package com.onboarding.controller;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class PhoneDto {
    private UUID userId;
    private UUID phoneId;
    private String phoneName;
    private String phoneNumber;
    private Boolean primaryPhoneNumber;
    private Boolean phoneNumberVerified;
}
