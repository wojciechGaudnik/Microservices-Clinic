import React from "react";
import {Button, Col, Form} from "react-bootstrap";
import {redirectByRole, useFormFields} from "../actions";

import {
    sendFetchRequestRegisterNewDoctor,
    styleForMainDiv, styleForForm, styleForFormLabel
} from "../containers/SetRegisterPage";


export const RegisterPage = (props) => {
    const [userRegisterInformation, setUserRegisterInformation] = useFormFields({
        firstName:  "",
        lastName:   "",
        licence:    "",
        photoURL:   "",
        email:      "",
        password:   "",
        role:       "doctor"
    });

    const handleChange = (event) => {
        console.log(event.target.value);
        setUserRegisterInformation(event);
    };

    //Main HTML return
    return(
        <div style={styleForMainDiv}>

        </div>
)};

export default RegisterPage