export const URLs = {
    REGISTER_USER:                  "http://localhost:8762/auth/users/",
    REGISTER_PATIENT:               "http://localhost:8762/patient-mssc/patients/",
    REGISTER_DOCTOR:                "http://localhost:8762/doctor-mssc/doctors/",
    GET_USER_INFORMATION:           "http://localhost:8762/doctor-mssc/doctors/",
    GET_DETAILS_BY_TOKEN:           "http://localhost:8762/auth/users/uuidAndRole/",
    LOGIN_USER:                     "http://localhost:8762/auth/login",
    CHANGE_DOCTOR_INFORMATION:      "http://localhost:8762/doctor-mssc/doctors/",
    DELETE_DOCTOR:                  "http://localhost:8762/doctor-mssc/doctors/",
    GET_ALL_DOCTOR_CALENDARS: (
            doctorUUID
        ) => (                      "http://localhost:8762/doctor-mssc/doctors/" + doctorUUID + "/calendars"),
    GET_ALL_APPOINTMENTS_IN_GIVEN_CALENDAR : (
            doctorUUID,
            calendarUUID
        ) => (                      "http://localhost:8762/doctor-mssc/doctors/" + doctorUUID + "/calendars/" + calendarUUID + "/appointments")
};

export const PATHs = {};