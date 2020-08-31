package com.onboarding.entity;

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
@Table(name = "usr")
@Getter
@Setter
@Accessors(chain = true)
public class User {

	@Id
	@Column(name = "usr_id")
	@Type(type="uuid-char")
	private UUID userId;
	@Column(name = "username")
	private String username;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

	public static User newInstance() {
		User user = new User();
		user.setUserId(UUID.randomUUID());
		return user;
	}
}
