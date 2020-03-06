import React, {useEffect, useState} from "react";

import {redirectByRole, useFormFields} from "../../../actions";

import {Button, Col, Form} from "react-bootstrap";
import {styleForForm, styleForFormLabel} from "../../../containers/SetFormForInputUserInformation";

import {EmailForm} from "./FormForInputUserInformationElements/EmailForm";
import {PasswordForm} from "./FormForInputUserInformationElements/PasswordForm";
import {RoleForm} from "./FormForInputUserInformationElements/RoleForm";
import {FirstNameForm} from "./FormForInputUserInformationElements/FirstNameForm";
import {LastNameForm} from "./FormForInputUserInformationElements/LastNameForm";
import {LicenceForm} from "./FormForInputUserInformationElements/LicenceForm";
import {PhotoURLForm} from "./FormForInputUserInformationElements/PhotoURLForm";


export const FormForInputUserInformation = (props) => {
    const [isFetchResponseOk, setIsFetchResponseOk] = useState(false);
    const [userInformation, setUserInformation] = useFormFields({
        firstName:  null,
        lastName:   null,
        licence:    null,
        photoUrl:   null,
        email:      null,
        password:   null,
        role:       "doctor"
    });

    const handleChange = (event) => {
        setUserInformation(event);
    };

    let sendFetchRequest = () => {
        const ifCatchSetErrorInStore = (error) => {props.setStoreError(error)};
        switch (props.variant) {
            case "register":
                props.fetchRequest(userInformation, {ifCatchSetErrorInStore});
                break;
            case "edit":
                console.log(props.userInformation);
                props.fetchRequest(userInformation, {ifCatchSetErrorInStore}, {uuid: props.userDetails.uuid});
                break;
        }
    };

    const whichFormVariant = () => {

    };

    useEffect(() => {
        whichFormVariant();
        if (!props.error && isFetchResponseOk){
            redirectByRole(null, props);
        }
    }, [isFetchResponseOk]);

    const onSubmit = (e) => {
        e.preventDefault();
        sendFetchRequest();
        setTimeout(() => {setIsFetchResponseOk(true)}, 1000);
    };

    return (
        <Form
            onSubmit={e => onSubmit(e)}
            style={styleForForm}>
            <Form.Row>
                {props.showEmailForm        ? ( <EmailForm      handleChange={handleChange}             />) : null}
                {props.showPasswordForm     ? ( <PasswordForm   handleChange={handleChange}             />) : null}
            </Form.Row>
            <Form.Row>
                {props.showRoleForm         ? ( <RoleForm       handleChange={handleChange}             /> ) : null}
                {props.showFirstNameForm    ? ( <FirstNameForm  handleChange={handleChange} {...props}  /> ) : null}
                {props.showLastNameForm     ? ( <LastNameForm   handleChange={handleChange} {...props}  /> ) : null}
            </Form.Row>
            <Form.Row>
                {props.showLicenceForm      ? ( <LicenceForm    handleChange={handleChange} {...props}  /> ) : null}
                {props.showPhotoURLForm     ? ( <PhotoURLForm   handleChange={handleChange} {...props}  /> ) : null}
            </Form.Row>
            <Button variant="light" type="submit"> {props.submitButtonTitle} </Button>
        </Form>
    );
};