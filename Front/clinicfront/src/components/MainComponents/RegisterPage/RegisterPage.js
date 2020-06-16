import React from "react";
import {FormForInputUserInformation} from "../../AdditionalComponents/FormForInputUserInfo/FormForInputUserInformation";
import AlertMessage from "../../AdditionalComponents/Alert/AlertMessage";
import {Container, Grid} from "@material-ui/core";
import {RoleForm} from "../../AdditionalComponents/RoleForm/RoleForm";
import ContainerForFormForInputUserInformation
  from "../../AdditionalComponents/FormForInputUserInfo/ContainerForFormForInputUserInformation";

export const RegisterPage = (props) => {

  const {
    fetchRegisterNewUser,
    registerStatus,
    dispatchRegisterStatus,
    handleChangeRole
  } = props;

  const whichForm = () => {
    switch (registerStatus.roleNewUser) {
      case "doctor":
        return(
        <ContainerForFormForInputUserInformation
          {...props}
          fetchRequest        ={(registerDetails) => fetchRegisterNewUser(registerDetails)}
          role                ={registerStatus.roleNewUser}
          primaryLabel        ="Zarejestruj"
          secondaryLabel      ="Register"
          showEmailForm       ={true}
          showPasswordForm    ={true}
          showFirstNameForm   ={true}
          showLastNameForm    ={true}
          showLicenceForm     ={true}
          showPhotoURLForm    ={true}
        >
          {({
              onSubmit,
              showEmailForm,
              showPasswordForm,
              showFirstNameForm,
              showLastNameForm,
              showLicenceForm,
              showPhotoURLForm,
              primaryLabel,
              secondaryLabel,
              submitButtonAvailable,
              validation,
              handleChange,
              setIsCorrectInputInForms
            }) => (
            <FormForInputUserInformation
              onSubmit={onSubmit}
              primaryLabel={primaryLabel}
              secondaryLabel={secondaryLabel}
              showEmailForm={showEmailForm}
              showPasswordForm={showPasswordForm}
              showFirstNameForm={showFirstNameForm}
              showLastNameForm={showLastNameForm}
              showLicenceForm={showLicenceForm}
              showPhotoURLForm={showPhotoURLForm}
              submitButtonAvailable={submitButtonAvailable}
              validation={validation}
              handleChange={handleChange}
              setIsCorrectInputInForms={setIsCorrectInputInForms}
            />
          )}
        </ContainerForFormForInputUserInformation>
        );
      case "patient":
        return(
        <ContainerForFormForInputUserInformation
          {...props}
          fetchRequest        ={(registerDetails) => fetchRegisterNewUser(registerDetails)}
          role                ={registerStatus.roleNewUser}
          primaryLabel        ="Zarejestruj"
          secondaryLabel      ="Register"
          showEmailForm       ={true}
          showPasswordForm    ={true}
          showFirstNameForm   ={true}
          showLastNameForm    ={true}
          showPhotoURLForm    ={true}
          showPeselForm       ={true}
        >
          {({
              onSubmit,
              showEmailForm,
              showPasswordForm,
              showFirstNameForm,
              showLastNameForm,
              showLicenceForm,
              showPhotoURLForm,
              showPeselForm,
              primaryLabel,
              secondaryLabel,
              submitButtonAvailable,
              validation,
              handleChange,
              setIsCorrectInputInForms
            }) => (
            <FormForInputUserInformation
              onSubmit={onSubmit}
              primaryLabel={primaryLabel}
              secondaryLabel={secondaryLabel}
              showEmailForm={showEmailForm}
              showPasswordForm={showPasswordForm}
              showFirstNameForm={showFirstNameForm}
              showLastNameForm={showLastNameForm}
              showLicenceForm={showLicenceForm}
              showPhotoURLForm={showPhotoURLForm}
              showPeselForm={showPeselForm}
              submitButtonAvailable={submitButtonAvailable}
              validation={validation}
              handleChange={handleChange}
              setIsCorrectInputInForms={setIsCorrectInputInForms}
            />
          )}
        </ContainerForFormForInputUserInformation>
        );
      default:
          return null;
    }
  };

  //Main HTML return
  return(
      <Container>
          <AlertMessage
              show={registerStatus.showSuccessMessage}
              onClose={() => dispatchRegisterStatus({type: "CLOSE_SUCCESS_MESSAGE"})}
              message="Successful Register"
              type="success"
          />
          <AlertMessage
              show={registerStatus.showFailMessage}
              onClose={() => dispatchRegisterStatus({type: "CLOSE_FAIL_MESSAGE"})}
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
              <Grid
                item
                xs={12}
              >
                  <RoleForm
                    handleChange={handleChangeRole}
                    role={registerStatus.roleNewUser}
                  />
              </Grid>
              <Grid
                item
                xs={12}
              >
                  {whichForm()}
              </Grid>
          </Grid>
      </Container>
)};

export default RegisterPage