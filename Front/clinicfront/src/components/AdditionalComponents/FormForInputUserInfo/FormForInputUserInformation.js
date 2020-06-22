import React from "react";

import {Button, CircularProgress} from "@material-ui/core";

import {EmailForm} from "./ElementsForFormForInputUserInformation/EmailForm";
import {PasswordForm} from "./ElementsForFormForInputUserInformation/PasswordForm";
import {FirstNameForm} from "./ElementsForFormForInputUserInformation/FirstNameForm";
import {LastNameForm} from "./ElementsForFormForInputUserInformation/LastNameForm";
import {LicenceForm} from "./ElementsForFormForInputUserInformation/LicenceForm";
import {PhotoURLForm} from "./ElementsForFormForInputUserInformation/PhotoURLForm";
import {Form} from "react-bootstrap";
import {PeselForm} from "./ElementsForFormForInputUserInformation/PeselForm";
import ButtonCustomTypography from "../CustomTypography/TypesOfCustomTypography/ButtonCustomTypography";
import Grid from "@material-ui/core/Grid";

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
    formComponentState,
    handleChange,
    setIsCorrectInputInForms,
    userInformation,
    primaryLabel,
    secondaryLabel,
    isLoading
  } = props;

  return (
    <Form
      onSubmit={e => onSubmit(e)}
    >
      <Form.Row>
        {showEmailForm        ? ( <EmailForm      handleChange={handleChange}             validation={formComponentState.validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
        {showPasswordForm     ? ( <PasswordForm   handleChange={handleChange}             validation={formComponentState.validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
      </Form.Row>
      <Form.Row>
        {showFirstNameForm    ? ( <FirstNameForm  handleChange={handleChange} userInformation={userInformation}  validation={formComponentState.validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
        {showLastNameForm     ? ( <LastNameForm   handleChange={handleChange} userInformation={userInformation}  validation={formComponentState.validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
      </Form.Row>
      <Form.Row>
        {showLicenceForm      ? ( <LicenceForm    handleChange={handleChange} userInformation={userInformation}  validation={formComponentState.validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
        {showPhotoURLForm     ? ( <PhotoURLForm   handleChange={handleChange} userInformation={userInformation}  validation={formComponentState.validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
      </Form.Row>
      <Form.Row>
        {showPeselForm        ? ( <PeselForm      handleChange={handleChange} userInformation={userInformation}  validation={formComponentState.validation} setIsCorrectInputInForms={setIsCorrectInputInForms}/> ) : null}
      </Form.Row>
      <Grid
        container
        direction="row"
        justify="flex-start"
        alignItems="center"
        spacing={1}
      >
        <Grid item>
          <Button
            variant="contained"
            color="primary"
            type="submit"
            size="small"
            disabled={!formComponentState.submitButtonAvailable && formComponentState.validation}
            disableElevation
          >
            <ButtonCustomTypography
              primaryLabel={primaryLabel}
              secondaryLabel={secondaryLabel}
            />
          </Button>
        </Grid>
        <Grid item style={{ paddingTop:"9px"}}>
          {isLoading ? (
            <CircularProgress
              size="1.5rem"
            />
          ) : null}
        </Grid>
      </Grid>
    </Form>
  );
};