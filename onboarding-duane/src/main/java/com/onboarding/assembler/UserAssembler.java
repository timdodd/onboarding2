package com.onboarding.assembler;

import com.onboarding.api.UserDto;
import com.onboarding.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAssembler {

	public List<UserDto> assemble(List<User> entities) {
		return entities.stream()
				.map(this::assemble)
				.collect(Collectors.toList());
	}

	public UserDto assemble(User entity) {
		return new UserDto()
				.setUserId(entity.getUserId())
				.setFirstName(entity.getFirstName())
				.setLastName(entity.getLastName())
				.setUsername(entity.getUsername());
	}

	public User disassemble(UserDto dto) {
		return disassembleInto(dto, User.newInstance());
	}

	public User disassembleInto(UserDto dto, User entity) {
		return entity
				.setFirstName(dto.getFirstName())
				.setLastName(dto.getLastName())
				.setUsername(dto.getUsername());
	}
}
