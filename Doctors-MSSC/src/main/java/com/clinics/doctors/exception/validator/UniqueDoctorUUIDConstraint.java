//package com.clinics.doctors.exception.validator;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.*;
//
//@Documented
//@Constraint(validatedBy = UniqueDoctorUUIDValidator.class)
//@Target({ElementType.TYPE, ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//public @interface UniqueDoctorUUIDConstraint {
//	String message() default "Doctor UUID already registered";
//	Class<?>[] groups() default {};
//	Class<? extends Payload>[] payload() default {};
//}
