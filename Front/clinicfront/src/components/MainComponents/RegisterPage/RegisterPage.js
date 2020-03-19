import React, {useEffect, useState} from "react";

import {sendFetchRequestRegisterNewUser} from "./SetRegisterPage";

import {FormForInputUserInformation} from "../../AdditionalComponents/FormForInputUserInfo/FormForInputUserInformation";

import AlertMessage from "../../AdditionalComponents/Alert/AlertMessage";

import {Container, Grid} from "@material-ui/core";

import {RoleForm} from "../../AdditionalComponents/RoleForm/RoleForm";


export const RegisterPage = (props) => {
    const { setStoreError, error } = props;

    const [registerAs, setRegisterAs] = useState("doctor");
    const [showSuccessMessage, setShowSuccessMessage] = useState(false);
    const [showErrorMessage, setShowErrorMessage] = useState(false);

    useEffect(() => {
        if (error.responseStatus === 201){setShowSuccessMessage(true)}
    }, [error]);

    useEffect(() => {
        setShowErrorMessage(error["isError"]);
    }, [error.isError, error]);

    const whichForm = () => {
        switch (registerAs) {
            case "doctor":
                return(
                    <FormForInputUserInformation
                        {...props}
                        fetchRequest        ={(registerDetails) => {
                            sendFetchRequestRegisterNewUser(
                                registerDetails,
                                {ifCatchSetErrorInStore: (error) => {setStoreError(error)}})
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
                                {ifCatchSetErrorInStore: (error) => {setStoreError(error)}})
                        }}
                        role                ={registerAs}
                        submitButtonTitle   ="Register"
                        showEmailForm       ={true}
                        showPasswordForm    ={true}
                        showFirstNameForm   ={true}
                        showLastNameForm    ={true}
                        showPhotoURLForm    ={true}
                        showPeselForm       ={true}
                    />
                );
            default:
                return null;
        }
    };

    //Main HTML return
    return(
        <Container>
            <AlertMessage
                show={showSuccessMessage}
                onClose={() => setShowSuccessMessage(false)}
                message="Successful Register"
                type="success"
            />
            <AlertMessage
                show={showErrorMessage}
                onClose={() => setStoreError({isError: false, responseStatus: null})}
                message="Wrong details please use another"
                type="error"
            />
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