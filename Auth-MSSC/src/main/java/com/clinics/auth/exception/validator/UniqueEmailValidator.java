package com.clinics.auth.exception.validator;

import com.clinics.auth.beans.BeansFactoryAuth;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint, String> {

	private EntityManager entityManager;

	@Override
	public void initialize(UniqueEmailConstraint constraintAnnotation) {
		entityManager = BeansFactoryAuth.getBean(EntityManager.class);
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
		try {
			entityManager.setFlushMode(FlushModeType.COMMIT);
			Query query = entityManager.createQuery("SELECT e FROM auth_user e WHERE e.email LIKE :email");
			query.setParameter("email", email);
			return query.getResultList().isEmpty();
		} finally {
			entityManager.setFlushMode(FlushModeType.AUTO);
		}
	}
}
