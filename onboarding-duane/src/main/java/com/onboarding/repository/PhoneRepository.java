package com.onboarding.repository;

import com.onboarding.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, UUID> {
    boolean existsByPhoneId(UUID phoneId);
    boolean existsPhoneByPhoneNumber(String phoneNumber);
    List<Phone> findAllByUserId(UUID userId);
}
