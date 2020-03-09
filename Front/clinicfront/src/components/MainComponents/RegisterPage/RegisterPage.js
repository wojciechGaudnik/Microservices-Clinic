import React, {useState} from "react";

import {sendFetchRequestRegisterNewDoctor} from "./SetRegisterPage";

import {FormForInputUserInformation} from "../../AdditionalComponents/FormForInputUseInfo/FormForInputUserInformation";

import {ErrorModal} from "../../AdditionalComponents/ErrorModal/ErrorModal";

import {Container} from "@material-ui/core";

import {RoleForm} from "../../AdditionalComponents/RoleForm/RoleForm";


export const RegisterPage = (props) => {
    const [registerAs, setRegisterAs] = useState("doctor");

    //Main HTML return
    return(
        <Container>
            {props.error ? (<ErrorModal modalTitle={"Wrong Input"} modalMessage={"Wrong login details"} />) : null}
            <RoleForm handleChange={setRegisterAs} role={registerAs}/>
            <FormForInputUserInformation
                {...props}
                fetchRequest        ={(registerDetails) => {
                    sendFetchRequestRegisterNewDoctor(
                        registerDetails,
                        {ifCatchSetErrorInStore: (error) => {props.setStoreError(error)}})
                }}
                submitButtonTitle   ="Register"
                showEmailForm       ={true}
                showPasswordForm    ={true}
                showRoleForm        ={true}
                showFirstNameForm   ={true}
                showLastNameForm    ={true}
                showLicenceForm     ={true}
                showPhotoURLForm    ={true}
            />
        </Container>
)};

export default RegisterPage