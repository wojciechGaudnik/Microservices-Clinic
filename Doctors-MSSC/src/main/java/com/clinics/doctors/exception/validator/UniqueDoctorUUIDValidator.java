package com.clinics.doctors.exception.validator;

import com.clinics.doctors.bean.BeanFactoryDoctor;
import com.clinics.doctors.model.Doctor;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class UniqueDoctorUUIDValidator implements ConstraintValidator<UniqueDoctorUUIDConstraint, Doctor> {

   private EntityManager entityManager;

   @Override
   public void initialize(UniqueDoctorUUIDConstraint constraintAnnotation) {
      entityManager = BeanFactoryDoctor.getBean(EntityManager.class);
   }

   @Override
   public boolean isValid(Doctor doctor, ConstraintValidatorContext constraintValidatorContext) {
      try {
         entityManager.setFlushMode(FlushModeType.COMMIT);
         Query query = entityManager.createQuery("SELECT d FROM doctor d WHERE d.doctoruuid=?1");
//         String query = String.format("SELECT e FROM %s e WHERE e.%suuid=?1", )
//         Query query = entityManager.createQuery("SELECT d FROM doctor d WHERE d.doctoruuid=?1");
         query.setParameter(1, doctor.getDoctoruuid());
         return query.getResultList().isEmpty();
      } finally {
         entityManager.setFlushMode(FlushModeType.AUTO);
      }
   }
}
