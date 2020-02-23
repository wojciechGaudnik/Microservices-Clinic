package com.clinics.auth.exception.validator;

import com.clinics.auth.bean.BeanFactoryAuth;
import com.clinics.auth.model.User;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint, User> {

	private EntityManager entityManager;

	@Override
	public void initialize(UniqueEmailConstraint constraintAnnotation) {
		entityManager = BeanFactoryAuth.getBean(EntityManager.class);
	}

	@Override
	public boolean isValid(User userToValid, ConstraintValidatorContext constraintValidatorContext) {
		var email = userToValid.getEmail();
		var ifSetIsEnable = userToValid.isEnable();
		try {
			entityManager.setFlushMode(FlushModeType.COMMIT);
			Query query = entityManager.createQuery("SELECT e FROM auth_user e WHERE e.email LIKE :email");
			query.setParameter("email", email);
//			if(!userToValid.isEnabled() && query.getResultList().isEmpty()) return true;
			if(query.getResultList().isEmpty()) return true;
			User userFromDB = (User) query.getSingleResult();
			entityManager.refresh(userFromDB);
			if(!userFromDB.isEnabled() && query.getResultList().size() == 1 && ifSetIsEnable) return true;
			if(query.getResultList().size() == 1) return false;
			return true;
		} finally {
			entityManager.setFlushMode(FlushModeType.COMMIT);
		}
	}
}
