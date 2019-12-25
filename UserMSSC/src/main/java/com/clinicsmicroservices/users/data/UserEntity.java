package com.clinicsmicroservices.users.data;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Entity(name = "users")
public class UserEntity implements Serializable {

	//	@Setter(AccessLevel.NONE)
//	@Getter(AccessLevel.NONE)
	private static final long serialVersionUID = 589812544161717802L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	@Email
	@Column(nullable = false, length = 120, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String userId;

	@Column(nullable = false, unique = true)
	private String encryptedPassword;

	@Column(nullable = false)
	private Date createAt;

	@PrePersist
	void createAt() {
		this.createAt = new Date();
	}
}
