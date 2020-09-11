package com.onboarding.service;

import com.onboarding.api.PhoneDto;
import com.onboarding.repository.PhoneRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.UUID;

public class PhoneValidatorTest {

//    @Test
//    public void testValidate_whenUseridInvalid_shouldCreateError(){
//        PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
//        PhoneValidator phoneValidator = new PhoneValidator(phoneRepository);
//        PhoneDto dto = getValidUserPhone();
//        Mockito.when(phoneRepository.existsByUserId(dto.getUserId())).thenReturn(false);
//        Map<String,String> errors = phoneValidator.validate(dto);
//
//        assertTrue(errors.size() == 1);
//        assertTrue(errors.containsKey("userId"));
//        assertEquals(PhoneValidator.USERID_INVALID, errors.get("userId"));
//    }

    @Test
    public void testValidate_whenPhoneNameNull_shouldCreateError() {
        PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
        PhoneValidator phoneValidator = new PhoneValidator(phoneRepository);
        PhoneDto dto = getValidUserPhone().setPhoneName(null);
        Map<String,String> errors = phoneValidator.validate(dto);

        assertTrue(errors.size() == 1);
        assertTrue(errors.containsKey("phoneName"));
        assertEquals(PhoneValidator.PHONE_NAME_REQUIRED, errors.get("phoneName"));
    }

    @Test
    public void testValidate_whenPhoneNumberNull_shouldCreateError(){
        PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
        PhoneValidator phoneValidator = new PhoneValidator(phoneRepository);
        PhoneDto dto = getValidUserPhone().setPhoneNumber(null);
        Map<String,String> errors = phoneValidator.validate(dto);

        assertTrue(errors.size() == 1);
        assertTrue(errors.containsKey("phoneNumber"));
        assertEquals(PhoneValidator.PHONE_NUMBER_REQUIRED, errors.get("phoneNumber"));
    }

    @Test
    public void testValidate_whenPhoneNameGreaterThanFifty_shouldCreateError() {
        PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
        PhoneValidator phoneValidator = new PhoneValidator(phoneRepository);
        String str = "11111111111111111111111111111111111111111111111111111111111111";
        PhoneDto dto = getValidUserPhone().setPhoneName(str);
        Map<String, String> errors = phoneValidator.validate(dto);

        assertTrue(errors.size() == 1);
        assertTrue(errors.containsKey("phoneName"));
        assertEquals(PhoneValidator.PHONE_NAME_LT_50, errors.get("phoneName"));
    }

    @Test
    public void testValidate_whenPhoneNumberGreaterThanFifty_shouldCreateError() {
        PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
        PhoneValidator phoneValidator = new PhoneValidator(phoneRepository);
        String str = "11111111111111111111111111111111111111111111111111111111111111";
        PhoneDto dto = getValidUserPhone().setPhoneNumber(str);
        Map<String, String> errors = phoneValidator.validate(dto);

        assertTrue(errors.size() == 1);
        assertTrue(errors.containsKey("phoneNumber"));
        assertEquals(PhoneValidator.PHONE_NUMBER_LT_50, errors.get("phoneNumber"));
    }

    @Test
    public void testValidate_whenPhoneNumberExists_shouldCreateError(){
        PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
        PhoneValidator phoneValidator = new PhoneValidator(phoneRepository);
        PhoneDto dto = getValidUserPhone();
        Mockito.when(phoneRepository.existsPhoneByPhoneNumber(dto.getPhoneNumber())).thenReturn(true);
        Map<String,String> errors = phoneValidator.validate(dto);

        assertTrue(errors.size() == 1);
        assertTrue(errors.containsKey("phoneNumber"));
        assertEquals(PhoneValidator.PHONE_NUMBER_TAKEN, errors.get("phoneNumber"));
    }

    @Test
    public void testValidate_whenPhoneNumberWrongFormat_shouldCreateError(){
        PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
        PhoneValidator phoneValidator = new PhoneValidator(phoneRepository);
        PhoneDto dto = getValidUserPhone().setPhoneNumber("2");
        Map<String,String> errors = phoneValidator.validate(dto);
        assertTrue(errors.size() == 1);
        assertTrue(errors.containsKey("phoneNumber"));
        assertEquals(PhoneValidator.PHONE_NUMBER_WRONG_FORMAT, errors.get("phoneNumber"));
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
