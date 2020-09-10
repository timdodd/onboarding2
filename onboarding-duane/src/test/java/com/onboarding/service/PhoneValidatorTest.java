package com.onboarding.service;

import com.onboarding.api.PhoneDto;
import com.onboarding.entity.Phone;
import com.onboarding.repository.PhoneRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

public class PhoneValidatorTest {

    @Test
    public void testValidate_whenPhoneNameNull_shouldCreateError() {
        PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
        PhoneValidator phoneValidator = new PhoneValidator(phoneRepository);
        PhoneDto dto = getValidPhone().setPhoneName(null);
        Map<String,String> errors = phoneValidator.validate(dto);

        assertTrue(errors.size() == 1);
        assertTrue(errors.containsKey("phoneName"));
        assertEquals(PhoneValidator.PHONE_NAME_REQUIRED, errors.get("phoneName"));
    }

    @Test
    public void testValidate_whenPhoneNumberNull_shouldCreateError(){
        PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
        PhoneValidator phoneValidator = new PhoneValidator(phoneRepository);
        PhoneDto dto = getValidPhone().setPhoneName(null);
        Map<String,String> errors = phoneValidator.validate(dto);

        assertTrue(errors.size() == 1);
        assertTrue(errors.containsKey("phoneNumber"));
        assertEquals(PhoneValidator.PHONE_NUMBER_REQUIRED, errors.get("phoneNumber"));
    }

    @Test
    public void testValidate_whenPhoneNumberExists_shouldCreateError(){
        PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
        PhoneValidator phoneValidator = new PhoneValidator(phoneRepository);
        PhoneDto dto = getValidPhone();
        Mockito.when(phoneRepository.existsPhoneByPhoneNumber(dto.getPhoneName())).thenReturn(true);
        Map<String,String> errors = phoneValidator.validate(dto);

        assertTrue(errors.size() == 1);
        assertTrue(errors.containsKey("phoneNumber"));
        assertEquals(PhoneValidator.PHONE_NUMBER_TAKEN, errors.get("phoneNumber"));
    }

    public PhoneDto getValidPhone() {
        return new PhoneDto()
                .setPhoneName("Duane's Phone")
                .setPhoneNumber("1-306-231-6467");
    }
}
