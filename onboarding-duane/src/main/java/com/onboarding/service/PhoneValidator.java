package com.onboarding.service;

import com.onboarding.api.PhoneDto;
import com.onboarding.api.UserDto;
import com.onboarding.exception.ValidationException;
import com.onboarding.repository.PhoneRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class PhoneValidator {

    static String PHONE_NAME_REQUIRED = "PHONE_NAME_REQUIRED";
    static String PHONE_NUMBER_REQUIRED = "PHONE_NUMBER_REQUIRED";
    static String PHONE_NAME_LT_50 = "PHONE_NAME_LT_50";
    static String PHONE_NUMBER_LT_50 = "PHONE_NUMBER_LT_50";
    static String PHONE_NUMBER_TAKEN = "PHONE_NUMBER_TAKEN";
    static String PHONE_NUMBER_WRONG_FORMAT = "PHONE_NUMBER_WRONG_FORMAT";

    private PhoneRepository phoneRepository;

    PhoneValidator(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public void validateAndThrow(PhoneDto dto) {
        Map<String, String> errors = validate(dto);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public Map<String, String> validate(PhoneDto dto) {
        Map<String, String> errors = new LinkedHashMap<>();

        if (StringUtils.isBlank(dto.getPhoneName())) {
            errors.put("phoneName", PHONE_NAME_REQUIRED);
        } else if(dto.getPhoneName().length() > 50) {
            errors.put("phoneName", PHONE_NAME_LT_50);
        }

        if (StringUtils.isBlank(dto.getPhoneNumber())) {
            errors.put("phoneNumber", PHONE_NUMBER_REQUIRED);
        } else if(dto.getPhoneNumber().length() > 50) {
            errors.put("phoneNumber", PHONE_NUMBER_LT_50);
        } else if(!dto.getPhoneNumber().matches("^\\d{11}")){
            errors.put("phoneNumber", PHONE_NUMBER_WRONG_FORMAT);
        } else if ((isNewPhone(dto) || isPhoneNumberEdited(dto))
                && phoneRepository.existsPhoneByPhoneNumber(dto.getPhoneNumber())) {
            errors.put("phoneNumber", PHONE_NUMBER_TAKEN);
        }
//        else if(phoneRepository.existsPhoneByPhoneNumber(dto.getPhoneNumber())) {
//            errors.put("phoneNumber", PHONE_NUMBER_TAKEN);
//        }

        return errors;
    }

    private boolean isNewPhone(PhoneDto dto) {
        return dto.getPhoneId() == null;
    }

    private boolean isPhoneNumberEdited(PhoneDto dto) {
        if (dto.getPhoneId() == null) {
            return false;
        }
        return phoneRepository.findById(dto.getPhoneId())
                .map(databaseUserPhone -> !databaseUserPhone.getPhoneNumber().equals(dto.getPhoneNumber()))
                .orElse(false);
    }

}
