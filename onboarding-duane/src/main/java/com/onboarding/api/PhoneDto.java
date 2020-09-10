package com.onboarding.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class PhoneDto {

    private UUID phoneId;
    private UUID userId;
    private Boolean primaryPhoneNumber;
    private String phoneName;
    private String phoneNumber;
}
