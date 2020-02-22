package com.clinics.auth.exception.validator;

import com.clinics.auth.bean.BeanFactoryAuth;
import com.clinics.auth.model.User;
import com.clinics.common.ConsoleColors;
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
		log.warn(ConsoleColors.RED);
		log.warn(userToValid + " <--- userToValid from validator");
		var email = userToValid.getEmail();
		try {
			log.warn("1");
			entityManager.setFlushMode(FlushModeType.COMMIT);
			Query query = entityManager.createQuery("SELECT e FROM auth_user e WHERE e.email LIKE :email");
			query.setParameter("email", email);
			if(!userToValid.isEnabled() && query.getResultList().isEmpty()) return true;
			log.warn("2");
			User userFromDB = (User) query.getSingleResult();
			entityManager.refresh(userFromDB);

			log.warn((query.getResultList().size() == 1) + "query.getResultList().size() == 1 <--------------------");
			if(!userFromDB.isEnabled() && query.getResultList().size() == 1) return true;
			log.warn("3");
//			var emailOld = userToValid.getEmail();
//			User userFromDB = (User) query.getSingleResult();
//			log.warn(emailOld + " <--- userFromDB is enabled ?");
//			entityManager.refresh(userFromDB);
//			log.warn(userFromDB.getEmail() + " <--- userToValid is enabled ?");
//			return
//					userToValid.getEmail().equals(userFromDB.getEmail())
//					&& userToValid.isEnabled()
//					&& !userFromDB.isEnabled();
			return false;
		} finally {
			log.warn("----> entityManager.setFlushMode(FlushModeType.AUTO); ");
			log.warn(ConsoleColors.RESET);
//			entityManager.flush();
			entityManager.setFlushMode(FlushModeType.COMMIT);
		}
	}
}
