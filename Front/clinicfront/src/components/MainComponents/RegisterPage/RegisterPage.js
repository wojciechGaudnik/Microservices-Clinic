import React, {useState} from "react";

import {sendFetchRequestRegisterNewUser} from "./SetRegisterPage";

import {FormForInputUserInformation} from "../../AdditionalComponents/FormForInputUseInfo/FormForInputUserInformation";

import {ErrorModal} from "../../AdditionalComponents/ErrorModal/ErrorModal";

import {Container, Grid} from "@material-ui/core";

import {RoleForm} from "../../AdditionalComponents/RoleForm/RoleForm";


export const RegisterPage = (props) => {
    const [registerAs, setRegisterAs] = useState("doctor");

    const whichForm = () => {
        switch (registerAs) {
            case "doctor":
                return(
                    <FormForInputUserInformation
                        {...props}
                        fetchRequest        ={(registerDetails) => {
                            sendFetchRequestRegisterNewUser(
                                registerDetails,
                                {ifCatchSetErrorInStore: (error) => {props.setStoreError(error)}})
                        }}
                        role                ={registerAs}
                        submitButtonTitle   ="Register"
                        showEmailForm       ={true}
                        showPasswordForm    ={true}
                        showFirstNameForm   ={true}
                        showLastNameForm    ={true}
                        showLicenceForm     ={true}
                        showPhotoURLForm    ={true}
                    />
                );
            case "patient":
                return(
                    <FormForInputUserInformation
                        {...props}
                        fetchRequest        ={(registerDetails) => {
                            sendFetchRequestRegisterNewUser(
                                registerDetails,
                                {ifCatchSetErrorInStore: (error) => {props.setStoreError(error)}})
                        }}
                        role                ={registerAs}
                        submitButtonTitle   ="Register"
                        showEmailForm       ={true}
                        showPasswordForm    ={true}
                        showFirstNameForm   ={true}
                        showLastNameForm    ={true}
                    />
                );
        }
    };

    //Main HTML return
    return(
        <Container>
            {props.error ? (<ErrorModal modalTitle={"Wrong Input"} modalMessage={"Wrong login details"} />) : null}
            <Grid
                container
                direction="column"
                justify="space-around"
                alignItems="stretch"
                spacing={5}
            >
                <Grid item xs={12}>
                    <RoleForm handleChange={setRegisterAs} role={registerAs}/>
                </Grid>
                <Grid item xs={12}>
                    {whichForm()}
                </Grid>
            </Grid>
        </Container>
)};

export default RegisterPage