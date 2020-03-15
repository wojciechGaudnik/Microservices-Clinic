import React, {useEffect, useState} from "react";

import {sendFetchRequestIsThereLoginUser, sendFetchRequestLoginUser} from "./SetLoginPage";

import {redirectByRole} from "../../../actions";

import {FormForInputUserInformation} from "../../AdditionalComponents/FormForInputUseInfo/FormForInputUserInformation";
import {Container} from "@material-ui/core";
import AlertMessage from "../../AdditionalComponents/Alert/AlertMessage";


export const LoginPage = (props) => {
    const { error, setStoreError, setStoreUserDetails } = props;
    const [showWarningMessage, setShowWarningMessage] = useState(error["isError"]);
    const [userDetails, setUserDetails] = useState({
        uuid: null,
        role: null
    });

    //Effects after each render
    useEffect(() => {
        setStoreUserDetails(userDetails);
        if (userDetails.role){redirectByRole(userDetails.role, props)}
    }, [userDetails.role, userDetails, props, setStoreUserDetails]);

    useEffect(() => {
        if (localStorage.token && !userDetails.role){sendFetchRequestIsThereLoginUser({setUserDetails})};
    }, [userDetails.role]);

    useEffect(() => {
        setShowWarningMessage(error["isError"]);
    }, [error.isError, error]);

    //Main HTML return
    return (
        <Container>
            <AlertMessage
                show={showWarningMessage}
                onClose={() => {setStoreError({isError: false, responseStatus: null})}}
                message="Wrong Login Details"
                type="error"
            />
            <FormForInputUserInformation
                {...props}
                fetchRequest        ={(userDetails) => {
                    sendFetchRequestLoginUser(
                        userDetails,
                        {setUserDetails},
                        {ifCatchSetErrorInStore: (error) => {setStoreError(error)}})
                }}
                submitButtonTitle   ="Log In"
                showEmailForm       ={true}
                showPasswordForm    ={true}
                showRoleForm        ={false}
                showFirstNameForm   ={false}
                showLastNameForm    ={false}
                showLicenceForm     ={false}
                showPhotoURLForm    ={false}
            />
        </Container>
    );
};

export default LoginPage
