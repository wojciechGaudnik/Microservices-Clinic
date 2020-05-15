import {useState} from "react";

export const setStoreUserDetails = userDetails => ({
    type: 'SET_USER_DETAILS',
    userDetails
});

export const setStoreDoctorInformation = userInformation => ({
    type: 'SET_DOCTOR_INFORMATION',
    userInformation
});

export const setStorePatientInformation = userInformation => ({
    type: 'SET_PATIENT_INFORMATION',
    userInformation
});

export const setStoreError = error => ({
    type: 'SET_ERROR',
    error
});

//USEFUL FUNCTIONS

export const useFormFields = (initialState) => {
    const [fields, setValues] = useState(initialState);

    return [
        fields,
        function(event) {
            if (event.target) {
                setValues({
                    ...fields,
                    [event.target.name]: event.target.value
                });
            } else {
                setValues(event)
            }
        }
    ];
};

export const logOut = (history) => {
    localStorage.removeItem("token");
    history.push("/");
};


export const redirectByRole = (role, props) => {
    switch (role) {
        case "doctor":
            props.history.push("/doctor");
            return;
        case "assistant":
            props.history.push("/assistant");
            return;
        case "patient":
            props.history.push("/patient");
            return;
        case "register":
            props.history.push("/register");
            return;
        default:
            props.history.push("/");
            return;
    }
};