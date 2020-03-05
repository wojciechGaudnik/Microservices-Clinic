import React from "react";

import {
    styleForMainDiv,
    sendFetchRequestRegisterNewDoctor
    } from "../containers/SetRegisterPage";

import {FormForInputUserInformation} from "./AdditionalComponents/FormForInputUserInformation";
import {ErrorModal} from "./AdditionalComponents/ErrorModal";


export const RegisterPage = (props) => {
    //Main HTML return
    return(
        <div style={styleForMainDiv}>
            {props.error ? (<ErrorModal/>) : null}
            <FormForInputUserInformation
                {...props}
                sendFetchRequest={sendFetchRequestRegisterNewDoctor}
                submitButtonTitle="Register"
                showEmailForm={true}
                showPasswordForm={true}
                showRoleForm={true}
                showFirstNameForm={true}
                showLastNameForm={true}
                showLicenceForm={true}
                showPhotoURLForm={true}
            />
        </div>
)};

export default RegisterPage