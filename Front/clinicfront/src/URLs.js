
export const URLs = {
  REGISTER_USER:                  "http://localhost:8762/auth/users/",
  REGISTER_PATIENT:               "http://localhost:8762/patient-mssc/patients",
  REGISTER_DOCTOR:                "http://localhost:8762/doctor-mssc/doctors/",
  GET_ALL_DOCTORS:                "http://localhost:8762/doctor-mssc/doctors",
  GET_DETAILS_BY_TOKEN:           "http://localhost:8762/auth/users/uuidAndRole/",
  LOGIN_USER:                     "http://localhost:8762/auth/login",
  CHANGE_PATIENT_INFORMATION: (patientUUID) => (`http://localhost:8762/patient-mssc/patients/${patientUUID}`),
  DELETE_PATIENT: (patientUUID) => (`http://localhost:8762/patient-mssc/patients/${patientUUID}`),
  GET_PATIENT_INFORMATION: (patientUUID) => (`http://localhost:8762/patient-mssc/patients/${patientUUID}`)        ,
  CHANGE_DOCTOR_INFORMATION: (doctorUUID) => (`http://localhost:8762/doctor-mssc/doctors/${doctorUUID}`)      ,
  DELETE_DOCTOR: (doctorUUID) => (`http://localhost:8762/doctor-mssc/doctors/${doctorUUID}`),
  GET_DOCTOR_INFORMATION: (doctorUUID) => (`http://localhost:8762/doctor-mssc/doctors/${doctorUUID}`),
  GET_ALL_DOCTOR_CALENDARS: (doctorUUID) => (`http://localhost:8762/doctor-mssc/doctors/${doctorUUID}/calendars`),
  GET_ALL_APPOINTMENTS_IN_GIVEN_CALENDAR : (doctorUUID, calendarUUID) => (`http://localhost:8762/doctor-mssc/doctors/${doctorUUID}/calendars/${calendarUUID}/appointments1`)
};