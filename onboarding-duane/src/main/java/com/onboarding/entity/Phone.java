package com.onboarding.entity;

import liquibase.util.BooleanUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "phone")
@Getter
@Setter
@Accessors(chain = true)
public class Phone {

    @Id
    @Column(name = "phone_id")
    @Type(type="uuid-char")
    private UUID phoneId;
    @Column(name = "user_id")
    @Type(type="uuid-char")
    private UUID userId;
    @Column(name = "primary_phone_number")
    private Boolean primaryPhoneNumber;
    @Column(name = "phone_name")
    private String phoneName;
    @Column(name = "phone_number")
    private String phoneNumber;

    public static Phone newInstance() {
        Phone phone = new Phone();
        phone.setPhoneId(UUID.randomUUID());
        return phone;
    }
}
