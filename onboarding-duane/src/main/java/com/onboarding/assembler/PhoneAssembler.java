package com.onboarding.assembler;

import com.onboarding.api.PhoneDto;
import com.onboarding.entity.Phone;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhoneAssembler {

    public List<PhoneDto> assemble (List<Phone> entities) {
        return entities.stream()
                .map(this::assemble)
                .collect(Collectors.toList());
    }

    public PhoneDto assemble(Phone entity) {
        return new PhoneDto()
                .setPhoneId(entity.getPhoneId())
                .setUserId(entity.getUserId())
                .setPhoneName(entity.getPhoneName())
                .setPhoneNumber(entity.getPhoneNumber())
                .setPrimaryPhoneNumber(entity.isPrimaryPhoneNumber())
                .setPhoneNumberVerified(entity.isPhoneNumberVerified());
    }

    public Phone disassemble(PhoneDto dto) {
        return disassembleInto(dto, Phone.newInstance());
    }

    public Phone disassembleInto(PhoneDto dto, Phone entity) {
        return entity
                .setUserId(dto.getUserId())
                .setPhoneName(dto.getPhoneName())
                .setPhoneNumber(dto.getPhoneNumber())
                .setPrimaryPhoneNumber(BooleanUtils.isTrue(dto.getPrimaryPhoneNumber()));
    }

}
