package com.onboarding.service;

import com.onboarding.api.PhoneDto;
import com.onboarding.assembler.PhoneAssembler;
import com.onboarding.entity.Phone;
import com.onboarding.exception.NotFoundException;
import com.onboarding.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private PhoneAssembler phoneAssembler;
    @Autowired
    private PhoneValidator phoneValidator;

    public PhoneDto create(PhoneDto dto) {
        phoneValidator.validateAndThrow(dto);

        if(dto.getPrimaryPhoneNumber()) {
            phoneRepository.findAllByUserId(dto.getUserId()).forEach(p -> p.setPrimaryPhoneNumber(false));
        }

        Phone entity = phoneAssembler.disassemble(dto);
        phoneRepository.save(entity);
        return phoneAssembler.assemble(entity);
    }

    public PhoneDto update(PhoneDto dto) {
        phoneValidator.validateAndThrow(dto);

        if(dto.getPrimaryPhoneNumber()) {
            phoneRepository.findAllByUserId(dto.getUserId()).forEach(p -> p.setPrimaryPhoneNumber(false));
        }

        return phoneRepository.findById(dto.getPhoneId())
                .map(entity -> phoneAssembler.disassembleInto(dto, entity))
                .map(phoneRepository::save)
                .map(phoneAssembler::assemble)
                .orElseThrow(() -> new NotFoundException());
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

}
