import React, {useState} from "react";
import {Modal} from "react-bootstrap";

export const setStoreUserDetails = userDetails => ({
    type: 'SET_USER_DETAILS',
    userDetails
});

export const setStoreUserInformation = userInformation => ({
    type: 'SET_USER_INFORMATION',
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
            setValues({
                ...fields,
                [event.target.name]: event.target.value
            });
        }
    ];
};

export const checkIsThereError = (props) => {
    if (localStorage.error){
        props.setStoreError(true);
        localStorage.removeItem("error");
    }
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
