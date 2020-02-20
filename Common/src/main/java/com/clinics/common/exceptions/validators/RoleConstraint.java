package com.clinics.common.exceptions.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoleValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleConstraint {
	String message() default "invalid role, allowed patient, doctor, assistant";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
