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
    closeAlertSuccessMessage,
    closeAlertFailedMessage,
    handleChangeRole,
  } = props;

  const whichForm = () => {
    switch (registerStatus.roleNewUser) {
      case "doctor":
        return(
          <ContainerForFormForInputUserInformation
            fetchRequest        ={(registerDetails) => fetchRegisterNewUser(registerDetails)}
            role                ={registerStatus.roleNewUser}
            isLoading           ={registerStatus.isLoading}
            primaryLabel        ="Zarejestruj"
            secondaryLabel      ="Register"
            showEmailForm       ={true}
            showPasswordForm    ={true}
            showFirstNameForm   ={true}
            showLastNameForm    ={true}
            showLicenceForm     ={true}
            showPhotoURLForm    ={true}
            showPeselForm       ={false}
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
                formComponentState,
                handleChange,
                setIsCorrectInputInForms,
                isLoading
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
                formComponentState={formComponentState}
                handleChange={handleChange}
                setIsCorrectInputInForms={setIsCorrectInputInForms}
                isLoading={isLoading}
              />
            )}
          </ContainerForFormForInputUserInformation>
        );
      case "patient":
        return(
          <ContainerForFormForInputUserInformation
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
            showLicenceForm     ={false}
          >
            {({
                onSubmit,
                showEmailForm,
                showPasswordForm,
                showFirstNameForm,
                showLastNameForm,
                showPhotoURLForm,
                showPeselForm,
                primaryLabel,
                secondaryLabel,
                formComponentState,
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
                showPhotoURLForm={showPhotoURLForm}
                showPeselForm={showPeselForm}
                formComponentState={formComponentState}
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
              onClose={() => closeAlertSuccessMessage()}
              message="Successful Register"
              type="success"
          />
          <AlertMessage
              show={registerStatus.showFailMessage}
              onClose={() => closeAlertFailedMessage()}
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