import React from "react";

import {
    styleForMainDiv,
    sendFetchRequestRegisterNewDoctor
    } from "../containers/SetRegisterPage";

import {FormForInputUserInformation} from "./AdditionalComponents/FormForInputUseInfo/FormForInputUserInformation";
import {ErrorModal} from "./AdditionalComponents/ErrorModal/ErrorModal";


export const RegisterPage = (props) => {
    //Main HTML return
    return(
        <div style={styleForMainDiv}>
            {props.error ? (<ErrorModal modalTitle={"Wrong Input"} modalMessage={"Wrong login details"} />) : null}
            <FormForInputUserInformation
                {...props}
                fetchRequest        ={sendFetchRequestRegisterNewDoctor}
                variant             ="register"
                submitButtonTitle   ="Register"
                showEmailForm       ={true}
                showPasswordForm    ={true}
                showRoleForm        ={true}
                showFirstNameForm   ={true}
                showLastNameForm    ={true}
                showLicenceForm     ={true}
                showPhotoURLForm    ={true}
            />
        </div>
)};

export default RegisterPage