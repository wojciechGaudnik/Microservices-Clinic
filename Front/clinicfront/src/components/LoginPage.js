import React, {useEffect, useState} from "react";

import {redirectByRole} from "../actions";

import {Button} from "react-bootstrap";

import {ErrorModal} from "./AdditionalComponents/ErrorModal/ErrorModal";

import {
    sendFetchRequestIsThereLoginUser,
    sendFetchRequestLoginUser,
    styleForButton,
    styleForMainDiv
} from "../containers/SetLoginPage";
import {FormForInputUserInformation} from "./AdditionalComponents/FormForInputUseInfo/FormForInputUserInformation";

export const LoginPage = (props) => {
    const [userDetails, setUserDetails] = useState({
        uuid: null,
        role: null
    });

    //Effects after each render
    useEffect(() => {
        if (localStorage.token && !userDetails.role){sendFetchRequestIsThereLoginUser({setUserDetails}); console.log("Works")}
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
        <div style={styleForMainDiv}>
            {props.error ? ( <ErrorModal modalTitle={"Wrong Input"} modalMessage={"Wrong login details"}/> ) : null}
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
        </div>
    );
};

export default LoginPage
