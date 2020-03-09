import React, {useEffect, useState} from "react";

import {Button} from "react-bootstrap";

import {
    sendFetchRequestIsThereLoginUser,
    sendFetchRequestLoginUser,
    styleForButton
} from "./SetLoginPage";

import {redirectByRole} from "../../../actions";

import {ErrorModal} from "../../AdditionalComponents/ErrorModal/ErrorModal";

import {FormForInputUserInformation} from "../../AdditionalComponents/FormForInputUseInfo/FormForInputUserInformation";
import {Container} from "@material-ui/core";

export const LoginPage = (props) => {
    const [userDetails, setUserDetails] = useState({
        uuid: null,
        role: null
    });

    //Effects after each render
    useEffect(() => {
        if (localStorage.token && !userDetails.role){sendFetchRequestIsThereLoginUser({setUserDetails})}
    }, []);
    useEffect(() => {
        props.setStoreUserDetails(userDetails);
        if (userDetails.role){redirectByRole(userDetails.role, props)}
    }, [userDetails]);

    const registerButtonClick = () => {
        props.setStoreError(false) ;
        redirectByRole("register", props)
    };

    //Main HTML return
    return (
        <Container>
            {props.error ? ( <ErrorModal modalTitle={"Wrong Input"}/> ) : null}
            <FormForInputUserInformation
                {...props}
                fetchRequest        ={(userDetails) => {
                    sendFetchRequestLoginUser(
                        userDetails,
                        {setUserDetails},
                        {ifCatchSetErrorInStore: (error) => {props.setStoreError(error)}})
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
            <Button
                variant="light"
                style={styleForButton}
                onClick={() => registerButtonClick()}>
                Register
            </Button>
        </Container>
    );
};

export default LoginPage
