package com.onboarding.repository;

import com.onboarding.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {


	boolean existsByUserId(UUID userId);

	//added
	boolean existsUserByUsername(String username);

	List<User> findAllByLastNameIgnoreCase(String lastName);
}
