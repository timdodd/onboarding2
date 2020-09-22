package com.onboarding.service;

import com.onboarding.api.UserDto;
import com.onboarding.assembler.UserAssembler;
import com.onboarding.entity.Phone;
import com.onboarding.entity.User;
import com.onboarding.exception.NotFoundException;
import com.onboarding.repository.PhoneRepository;
import com.onboarding.repository.UserRepository;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserAssembler userAssembler;
	@Autowired
	private UserValidator userValidator;
	@Autowired
	private PhoneService phoneService;

	private PhoneRepository phoneRepository;
	UserService(PhoneRepository phoneRepository) {
		this.phoneRepository = phoneRepository;
	}

	public UserDto update(UserDto dto) {
		userValidator.validateAndThrow(dto);
		return userRepository.findById(dto.getUserId())
				.map(entity -> userAssembler.disassembleInto(dto, entity))
				.map(userRepository::save)
				.map(userAssembler::assemble)
				.orElseThrow(() -> new NotFoundException());
	}

	public UserDto create(UserDto dto) {
		userValidator.validateAndThrow(dto);
		User entity = userAssembler.disassemble(dto);
		userRepository.save(entity);
		return userAssembler.assemble(entity);

//		return Optional.of(dto)
//				.map(userAssembler::disassemble)
//				.map(userRepository::save)
//				.map(userAssembler::assemble)
//				.orElseThrow(() -> new RuntimeException("Oh no!"));
	}

	public UserDto get(UUID userId) {
		return userRepository.findById(userId)
				.map(userAssembler::assemble)
				.orElseThrow(() -> new NotFoundException());
	}

	public List<UserDto> findAll() {
		return userAssembler.assemble(userRepository.findAll());
	}

	public List<UserDto> findAllByLastName(String lastName) {
		return userAssembler.assemble(userRepository.findAllByLastNameIgnoreCase(lastName));
	}

	public void delete(UUID userId) {
		boolean exists = userRepository.existsByUserId(userId);
		if (exists) {
			userRepository.deleteById(userId);
			List<Phone> phones = phoneRepository.findAllByUserId(userId);
			phones.forEach(phone -> phoneService.delete(phone.getPhoneId()));
		} else {
			throw new NotFoundException();
		}
	}
}
