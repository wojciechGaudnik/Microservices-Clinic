import React, {useEffect, useState} from "react";

import {Button} from "@material-ui/core";

import {useFormFields} from "../../../actions";

import {EmailForm} from "./ElementsForFormForInputUserInformation/EmailForm";
import {PasswordForm} from "./ElementsForFormForInputUserInformation/PasswordForm";
import {FirstNameForm} from "./ElementsForFormForInputUserInformation/FirstNameForm";
import {LastNameForm} from "./ElementsForFormForInputUserInformation/LastNameForm";
import {LicenceForm} from "./ElementsForFormForInputUserInformation/LicenceForm";
import {PhotoURLForm} from "./ElementsForFormForInputUserInformation/PhotoURLForm";
import {Form} from "react-bootstrap";
import {PeselForm} from "./ElementsForFormForInputUserInformation/PeselForm";


export const FormForInputUserInformation = (props) => {
    const {
        role,
        fetchRequest,
        showEmailForm,
        showPasswordForm,
        showFirstNameForm,
        showLastNameForm,
        showLicenceForm,
        showPhotoURLForm,
        showPeselForm,
        submitButtonTitle
    } = props;

    const [registerAs, setRegisterAs] = useState(role);
    const [userInformation, setUserInformation] = useFormFields({
        firstName:  null,
        lastName:   null,
        licence:    null,
        photoUrl:   null,
        email:      null,
        password:   null,
        pesel:      null,
    });

    useEffect(() => {
        setRegisterAs(role);
    }, [role, setRegisterAs]);

    const handleChange = (event) => {
        setUserInformation(event);
    };

    const onSubmit = (e) => {
        e.preventDefault();
        fetchRequest({...userInformation, role: registerAs});
    };

    return (
        <Form
            onSubmit={e => onSubmit(e)}
        >
            <Form.Row>
                {showEmailForm        ? ( <EmailForm      handleChange={handleChange}             />) : null}
                {showPasswordForm     ? ( <PasswordForm   handleChange={handleChange}             />) : null}
            </Form.Row>
            <Form.Row>
                {showFirstNameForm    ? ( <FirstNameForm  handleChange={handleChange} {...props}  /> ) : null}
                {showLastNameForm     ? ( <LastNameForm   handleChange={handleChange} {...props}  /> ) : null}
            </Form.Row>
            <Form.Row>
                {showLicenceForm      ? ( <LicenceForm    handleChange={handleChange} {...props}  /> ) : null}
                {showPhotoURLForm     ? ( <PhotoURLForm   handleChange={handleChange} {...props}  /> ) : null}
            </Form.Row>
            <Form.Row>
                {showPeselForm        ? ( <PeselForm      handleChange={handleChange} {...props}  /> ) : null}
            </Form.Row>
            <Button variant="contained"
                    color="primary"
                    type="submit"
                    disableElevation
            >
                {submitButtonTitle}
            </Button>
        </Form>
    );
};