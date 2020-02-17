package com.clinics.auth.exception.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmailConstraint {
	String message() default "mail already registered";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
