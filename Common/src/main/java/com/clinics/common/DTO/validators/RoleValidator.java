package com.clinics.common.DTO.validators;

import com.clinics.common.security.Role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<RoleConstraint, String>, Role {
	@Override
	public void initialize(RoleConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
		return getAllRoles().stream()
				.filter(r -> r.equals(role)
						&& !r.equals(Role.SYSTEM_ADMIN))
				.count() == 1;
	}
}
