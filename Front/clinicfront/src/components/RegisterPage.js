import React from "react";

import {useFormFields} from "../actions";

import {styleForMainDiv} from "../containers/SetRegisterPage";

import {FormForInputUserInformation} from "./FormForInputUserInformation";


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

    //Main HTML return
    return(
        <div style={styleForMainDiv}>
            <FormForInputUserInformation {...props} setFunction={setUserRegisterInformation} userInformation = {userRegisterInformation} variant="register"/>
        </div>
)};

export default RegisterPage