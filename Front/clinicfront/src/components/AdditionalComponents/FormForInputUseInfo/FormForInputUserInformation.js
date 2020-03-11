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
import {keys} from "@material-ui/core/styles/createBreakpoints";


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

    const [validation, setValidation] = useState(true);
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
    const [isCorrectInputInEachForms, setIsCorrectInputFromEachForms] = useState({
        emailForm:      !showEmailForm,
        firstNameForm:  !showFirstNameForm,
        lastNameForm:   !showLastNameForm,
        licenceForm:    !showLicenceForm,
        passwordForm:   !showPasswordForm,
        peselForm:      !showPeselForm ,
        photoURLForm:   !showPhotoURLForm,
    });

    useEffect(() => {
        setRegisterAs(role);
        setIsCorrectInputFromEachForms({
            emailForm:      !showEmailForm,
            firstNameForm:  !showFirstNameForm,
            lastNameForm:   !showLastNameForm,
            licenceForm:    !showLicenceForm,
            passwordForm:   !showPasswordForm,
            peselForm:      !showPeselForm ,
            photoURLForm:   !showPhotoURLForm,
        });
    }, [role,
        setRegisterAs,
        showEmailForm,
        showFirstNameForm,
        showLastNameForm,
        showLicenceForm,
        showPasswordForm,
        showPeselForm ,
        showPhotoURLForm,]);

    useEffect(() => {
        setValidation(submitButtonTitle !== "Log In")
    }, [submitButtonTitle]);

    const setIsCorrectInputInForms = (isInputCorrectObject) => {
        console.log(isInputCorrectObject);
        setIsCorrectInputFromEachForms({...isCorrectInputInEachForms, ...isInputCorrectObject}); console.log("Works")};

    const checkCorrectInputInAllFormsForButtonAvailable = () => {
        console.log(isCorrectInputInEachForms);
        for (const isCorrectInput in isCorrectInputInEachForms){
            if (isCorrectInputInEachForms[isCorrectInput] === false){
                return false;
            }
        }
        return true;
    };

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
                {showEmailForm        ? ( <EmailForm      handleChange={handleChange}             validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
                {showPasswordForm     ? ( <PasswordForm   handleChange={handleChange}             validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
            </Form.Row>
            <Form.Row>
                {showFirstNameForm    ? ( <FirstNameForm  handleChange={handleChange} {...props}  validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
                {showLastNameForm     ? ( <LastNameForm   handleChange={handleChange} {...props}  validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
            </Form.Row>
            <Form.Row>
                {showLicenceForm      ? ( <LicenceForm    handleChange={handleChange} {...props}  validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
                {showPhotoURLForm     ? ( <PhotoURLForm   handleChange={handleChange} {...props}  validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
            </Form.Row>
            <Form.Row>
                {showPeselForm        ? ( <PeselForm      handleChange={handleChange} {...props}  validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
            </Form.Row>
            <Button variant="contained"
                    color="primary"
                    type="submit"
                    disabled={!checkCorrectInputInAllFormsForButtonAvailable() && validation}
                    disableElevation
            >
                {submitButtonTitle}
            </Button>
        </Form>
    );
};