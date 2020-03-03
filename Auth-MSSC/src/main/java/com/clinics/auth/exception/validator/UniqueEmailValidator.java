//package com.clinics.auth.exception.validator;
//
//import com.clinics.auth.bean.BeanFactoryAuth;
//import com.clinics.auth.ui.model.User;
//import com.clinics.auth.ui.repositorie.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.*;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//
//@Slf4j
//public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint, User> {
//
//
//	private User userFromDB;
//
////	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Autowired
//	UserRepository userRepository;
//
//	@PersistenceContext
//	@Override
//	public void initialize(UniqueEmailConstraint constraintAnnotation) {
//		entityManager = BeanFactoryAuth.getBean(EntityManager.class);
//
//		userFromDB = userRepository.findByEmail()
//
//	}
//
//	// tod bootstrap
//	// tod set enable
//	// todo add
//	// todo update
//
//
//	// todo https://digitaljoel.nerd-herders.com/2010/12/28/spring-mvc-and-jsr-303-validation-groups/
//	@Override
//	@Transactional
//	public boolean isValid(User userToValid, ConstraintValidatorContext constraintValidatorContext) {
//		final var userToValidId = userToValid.getId();
//		final var userToValidEmail = userToValid.getEmail();
//		final var userToValidEnable = userToValid.isEnable();
//		final var userToValidPassword = userToValid.getPassword();
//		final var userToValidUUID = userToValid.getUuid();
//		log.error("---> user to valid: \n" + userToValidId + " : " + userToValidEmail + " : " + userToValidEnable + " : " + userToValidPassword + " : " + userToValidUUID);
//		try {
//			log.error("0");
//
//			entityManager.setFlushMode(FlushModeType.AUTO);
//
//
//			Query query = entityManager.createQuery("SELECT e FROM auth_user e WHERE e.email LIKE :email");
//			query.setParameter("email", userToValidEmail);
//
//			log.error("1");
//			if (query.getResultList().isEmpty()) return true;
//			log.error("2");
//			User userFromDB = (User) query.getSingleResult();
//			log.error("3");
////			entityManager.refresh(userFromDB);
//			log.error("4");
//			final var userFromDBId = userFromDB.getId();
//			final var userFromDBEmail = userFromDB.getEmail();
//			final var userFromDBEnable = userFromDB.isEnable();
//			final var userFromDBPassword = userFromDB.getPassword();
//			final var userFromDBUUID = userFromDB.getUuid();
//			log.error("---> user from db after: \n" + userFromDBId + " : " + userFromDBEmail + " : " + userFromDBEnable + " : " + userFromDBPassword + " : " + userFromDBUUID);
//			log.error(String.valueOf(userFromDB.equals(userToValid)));
//			if(userFromDB.equals(userToValid)) return true;
//
////			//todo set user enable
////			if (userToValidId.equals(userFromDBId) &&
////					userToValidEmail.equals(userFromDBEmail) &&
////					userToValidEnable &&
////					!userFromDBEnable) {
////
////				userFromDB.setEnable(true);
//////				entityManager.merge(userFromDB);
////				return true;
////			}
////			log.error("5");
////
////			//todo edit pass
////			if (userToValidId.equals(userFromDBId) &&
////					userToValidEmail.equals(userFromDBEmail) &&
////					userToValidEnable &&
////					userFromDBEnable) {
////				return true;
////			}
////			log.error("6");
//
////			if (!userToValidId.equals(userFromDBId) &&
////					userToValidEmail.equals(userFromDBEmail)
////					) {
////				log.error("----> ");
////				return false;
////			}
//
//
//
////			if(userToValidId == null && query.getResultList().size() == 1) return false;
//
////			log.error("---> user data from db after refresh:" + userFromDBId + " : " + userFromDBEmail + " : " + userFromDBEnable );
////			log.error("---> user from db:" + userFromDB);
////			log.error("---> last:" + userToValidId + " : " + userFromDBId + " : "  + userToValidEmail + " : " + userFromDBEmail);
////			if(!userFromDB.isEnabled() && query.getResultList().size() == 1 && userToValidEnable) return true;
////			if (userToValidId != null && userToValidId.equals(userFromDBId) && userToValidEmail.equals(userFromDBEmail))
////				return true;
////			log.error("---> last last" + userToValidId + " : " + userFromDBId + " : "  + userToValidEmail + " : " + userFromDBEmail);
////			if (userToValidId != null && !userToValidId.equals(userFromDBId) && userToValidEmail.equals(userFromDBEmail))
////				return false;
////
//////			if(userToValidId == null && userFromDBEmail.equals(userToValidEmail)) return false;
////			return query.getResultList().size() != 1;
//			log.error("---> last <---");
////			return false;
//		} catch (Exception e) {
//			log.error("---> error from catch: " + e.getLocalizedMessage());
//			log.error("---> error from catch: " + e.getMessage());
//		} finally {
////			EntityTransaction test = entityManager.getTransaction();
////			test.commit();
//			log.error("---> from finally    ");
//			entityManager.setFlushMode(FlushModeType.AUTO);
//		}
//		return false;
//	}
//}
