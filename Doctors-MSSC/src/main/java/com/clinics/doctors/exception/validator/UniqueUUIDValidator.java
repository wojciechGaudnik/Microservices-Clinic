package com.clinics.doctors.exception.validator;

import com.clinics.doctors.beans.BeansFactoryDoctor;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class UniqueUUIDValidator implements ConstraintValidator<UniqueUUIDConstraint, UUID> {

   private EntityManager entityManager;

   @Override
   public void initialize(UniqueUUIDConstraint constraintAnnotation) {
      entityManager = BeansFactoryDoctor.getBean(EntityManager.class);
   }

   @Override
   public boolean isValid(UUID doctoruuid, ConstraintValidatorContext constraintValidatorContext) {
      try {
         entityManager.setFlushMode(FlushModeType.COMMIT);
         Query query = entityManager.createQuery("SELECT d FROM doctor d WHERE d.doctoruuid=?1");
         query.setParameter(1, doctoruuid);
         return query.getResultList().isEmpty();
      } finally {
         entityManager.setFlushMode(FlushModeType.AUTO);
      }
   }
}
