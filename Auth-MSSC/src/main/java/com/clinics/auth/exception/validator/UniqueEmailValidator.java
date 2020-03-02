package com.clinics.auth.exception.validator;

import com.clinics.auth.bean.BeanFactoryAuth;
import com.clinics.auth.ui.model.User;
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

	// todo https://digitaljoel.nerd-herders.com/2010/12/28/spring-mvc-and-jsr-303-validation-groups/
	@Override
	public boolean isValid(User userToValid, ConstraintValidatorContext constraintValidatorContext) {
//		if(userToValid.getEmail() == null) return true;
		var userToValidEmail = userToValid.getEmail();
		var userToValidEnable = userToValid.isEnable();
		var userToValidId = userToValid.getId();
		log.warn("---> user to valid " + userToValid);
		try {
			entityManager.setFlushMode(FlushModeType.COMMIT);
			Query query = entityManager.createQuery("SELECT e FROM auth_user e WHERE e.email LIKE :email");
			query.setParameter("email", userToValidEmail);
			if(query.getResultList().isEmpty()) return true;
			User userFromDB = (User) query.getSingleResult();
			entityManager.refresh(userFromDB);
			log.warn("---> user from db " + userFromDB);
			if(!userFromDB.isEnabled() && query.getResultList().size() == 1 && userToValidEnable) return true;
			if(userFromDB.getEmail().equals(userToValidEmail)) return true;
			return query.getResultList().size() != 1;
		} finally {
			entityManager.setFlushMode(FlushModeType.COMMIT);
		}
	}
}
