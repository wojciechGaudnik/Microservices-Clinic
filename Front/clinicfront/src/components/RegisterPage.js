import React from "react";

import {
    styleForMainDiv,
    sendFetchRequestRegisterNewDoctor
    } from "../containers/SetRegisterPage";

import {FormForInputUserInformation} from "./FormForInputUserInformation";


export const RegisterPage = (props) => {
    //Main HTML return
    return(
        <div style={styleForMainDiv}>
            <FormForInputUserInformation {...props} sendFetchRequest={sendFetchRequestRegisterNewDoctor} variant="register"/>
        </div>
)};

export default RegisterPage