import React, {useEffect} from "react";

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
    const [userInformation, setUserInformation] = useFormFields({
        firstName:  null,
        lastName:   null,
        licence:    null,
        photoUrl:   null,
        email:      null,
        password:   null,
        pesel:      null,
        role:       props.role
    });

    useEffect(() => {
        setUserInformation({
            ...userInformation,
            role: props.role
        })
    }, [props.role]);

    const handleChange = (event) => {
        setUserInformation(event);
        console.log(userInformation)
    };

    const onSubmit = (e) => {
        e.preventDefault();
        props.fetchRequest(userInformation);
    };

    return (
        <Form
            onSubmit={e => onSubmit(e)}
        >
            <Form.Row>
                {props.showEmailForm        ? ( <EmailForm      handleChange={handleChange}             />) : null}
                {props.showPasswordForm     ? ( <PasswordForm   handleChange={handleChange}             />) : null}
            </Form.Row>
            <Form.Row>
                {props.showFirstNameForm    ? ( <FirstNameForm  handleChange={handleChange} {...props}  /> ) : null}
                {props.showLastNameForm     ? ( <LastNameForm   handleChange={handleChange} {...props}  /> ) : null}
            </Form.Row>
            <Form.Row>
                {props.showLicenceForm      ? ( <LicenceForm    handleChange={handleChange} {...props}  /> ) : null}
                {props.showPhotoURLForm     ? ( <PhotoURLForm   handleChange={handleChange} {...props}  /> ) : null}
            </Form.Row>
            <Form.Row>
                {props.showPeselForm        ? ( <PeselForm      handleChange={handleChange} {...props}  /> ) : null}
            </Form.Row>
            <Button variant="contained"
                    color="primary"
                    type="submit"
                    disableElevation
            >
                {props.submitButtonTitle}
            </Button>
        </Form>
    );
};