package com.clinics.doctors.model;

import com.clinics.doctors.beans.BeansFactoryDoctor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

@Slf4j
public class UniqueUUIDValidator implements ConstraintValidator<UniqueUUIDConstraint, UUID> {

   private EntityManager entityManager;

   @Override
   public void initialize(UniqueUUIDConstraint constraintAnnotation) {
      entityManager = BeansFactoryDoctor.getBean(EntityManager.class);
   }

   @Override
   public boolean isValid(UUID doctor_uuid, ConstraintValidatorContext constraintValidatorContext) {
      try {
         log.error("1 <-----------------------------------------------------------------");
         entityManager.setFlushMode(FlushModeType.COMMIT);
         log.error("2 <-----------------------------------------------------------------");
//         return true;
//         Query query = entityManager.createQuery("SELECT e FROM doctor e WHERE e.doctor_uuid=?1");
         Query query = entityManager.createQuery("SELECT d FROM doctor d WHERE d.doctor_uuid=?1");
         log.error("3 <-----------------------------------------------------------------");
         UUID uuid = UUID.fromString("1766eb04-e011-487e-96a8-535891c5300e");
         query.setParameter(1, doctor_uuid);
         log.error("4 <-----------------------------------------------------------------");
         log.error(String.valueOf(query));
         log.error("5 <-----------------------------------------------------------------");
         log.error(String.valueOf(query.getResultList()));
         log.error("6 <-----------------------------------------------------------------");
//                  return true;
         return query.getResultList().isEmpty();
      } finally {
         entityManager.setFlushMode(FlushModeType.AUTO);
      }
//      return true;
   }
}
