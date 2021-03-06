package com.onboarding.service;

import com.onboarding.UsersApplication;
import com.onboarding.api.PhoneDto;
import com.onboarding.assembler.PhoneAssembler;
import com.onboarding.entity.Phone;
import com.onboarding.exception.NotFoundException;
import com.onboarding.repository.PhoneRepository;
import com.onboarding.repository.UserRepository;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class PhoneService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private PhoneAssembler phoneAssembler;
    @Autowired
    private PhoneValidator phoneValidator;

    public PhoneDto create(PhoneDto dto) {
        if(!userRepository.existsByUserId(dto.getUserId())) {
            throw new NotFoundException();
        }
        phoneValidator.validateAndThrow(dto);

        if (BooleanUtils.isTrue(dto.getPrimaryPhoneNumber())) {
            markPhoneNumbersNotPrimary(dto.getUserId());
        }

        Phone entity = phoneAssembler.disassemble(dto);
        phoneRepository.save(entity);
        return phoneAssembler.assemble(entity);
    }

    public PhoneDto update(PhoneDto dto) {
        phoneValidator.validateAndThrow(dto);

        if (BooleanUtils.isTrue(dto.getPrimaryPhoneNumber())) {
            markPhoneNumbersNotPrimary(dto.getUserId(), (phoneId) -> !dto.getPhoneId().equals(phoneId));
        }

        return phoneRepository.findById(dto.getPhoneId())
                .map(entity -> phoneAssembler.disassembleInto(dto, entity))
                .map(phoneRepository::save)
                .map(phoneAssembler::assemble)
                .orElseThrow(() -> new NotFoundException());
    }


    private void markPhoneNumbersNotPrimary(UUID userId) {
        markPhoneNumbersNotPrimary(userId, (phoneId) -> true);
    }

    private void markPhoneNumbersNotPrimary(UUID userId, Predicate<UUID> exclude) {
        phoneRepository.findAllByUserId(userId)
                .stream()
                .filter(p -> exclude.test(p.getPhoneId()))
                .forEach(p -> p.setPrimaryPhoneNumber(false));
    }


    public void delete(UUID phoneId) {
        boolean exists = phoneRepository.existsByPhoneId(phoneId);
        if (exists) {
            phoneRepository.deleteById(phoneId);
        } else {
            throw new NotFoundException();
        }
    }

    public PhoneDto get(UUID phoneId) {
        return phoneRepository.findById(phoneId)
                .map(phoneAssembler::assemble)
                .orElseThrow(() -> new NotFoundException());
    }

    public List<PhoneDto> findAllPhones() {
        return phoneAssembler.assemble(phoneRepository.findAll());
    }

    public List<PhoneDto> findAllByUserId(UUID userId){
        return phoneAssembler.assemble(phoneRepository.findAllByUserId(userId));
    }

    public void sendVerify(PhoneDto dto) {
        Verification.creator(
                UsersApplication.SID,
                "+" + dto.getPhoneNumber(),
                "sms")
                .create();
    }

    public void checkVerify(PhoneDto dto, String code) {
        VerificationCheck verificationCheck = VerificationCheck.creator(
                UsersApplication.SID,
                code)
                .setTo("+" + dto.getPhoneNumber()).create();

        if(verificationCheck.getStatus().equals("approved")) {
            dto.setPhoneNumberVerified(true);
            update(dto);
        }
    }

}
