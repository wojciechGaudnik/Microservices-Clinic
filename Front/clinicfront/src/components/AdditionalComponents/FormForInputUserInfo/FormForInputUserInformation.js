import React from "react";

import {Button} from "@material-ui/core";

import {EmailForm} from "./ElementsForFormForInputUserInformation/EmailForm";
import {PasswordForm} from "./ElementsForFormForInputUserInformation/PasswordForm";
import {FirstNameForm} from "./ElementsForFormForInputUserInformation/FirstNameForm";
import {LastNameForm} from "./ElementsForFormForInputUserInformation/LastNameForm";
import {LicenceForm} from "./ElementsForFormForInputUserInformation/LicenceForm";
import {PhotoURLForm} from "./ElementsForFormForInputUserInformation/PhotoURLForm";
import {Form} from "react-bootstrap";
import {PeselForm} from "./ElementsForFormForInputUserInformation/PeselForm";
import CustomTypography from "../CustomTypography/CustomTypography";

export const FormForInputUserInformation = (props) => {
  const {
    onSubmit,
    showEmailForm,
    showPasswordForm,
    showFirstNameForm,
    showLastNameForm,
    showLicenceForm,
    showPhotoURLForm,
    showPeselForm,
    submitButtonAvailable,
    validation,
    handleChange,
    setIsCorrectInputInForms,
    userInformation,
    primaryLabel,
    secondaryLabel
  } = props;

  return (
    <Form
      onSubmit={e => onSubmit(e)}
    >
      <Form.Row>
        {showEmailForm        ? ( <EmailForm      handleChange={handleChange}             validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
        {showPasswordForm     ? ( <PasswordForm   handleChange={handleChange}             validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
      </Form.Row>
      <Form.Row>
        {showFirstNameForm    ? ( <FirstNameForm  handleChange={handleChange} userInformation={userInformation}  validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
        {showLastNameForm     ? ( <LastNameForm   handleChange={handleChange} userInformation={userInformation}  validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
      </Form.Row>
      <Form.Row>
        {showLicenceForm      ? ( <LicenceForm    handleChange={handleChange} userInformation={userInformation}  validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
        {showPhotoURLForm     ? ( <PhotoURLForm   handleChange={handleChange} userInformation={userInformation}  validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
      </Form.Row>
      <Form.Row>
        {showPeselForm        ? ( <PeselForm      handleChange={handleChange} userInformation={userInformation}  validation={validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
      </Form.Row>
      <Button
        variant="contained"
        color="primary"
        type="submit"
        disabled={!submitButtonAvailable && validation}
        disableElevation
      >
        <CustomTypography
          primaryLabel={primaryLabel}
          secondaryLabel={secondaryLabel}
        />
      </Button>
    </Form>
  );
};