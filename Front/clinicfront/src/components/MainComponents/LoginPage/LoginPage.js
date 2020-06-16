import React from "react";
import {FormForInputUserInformation} from "../../AdditionalComponents/FormForInputUserInfo/FormForInputUserInformation";
import {Container} from "@material-ui/core";
import AlertMessage from "../../AdditionalComponents/Alert/AlertMessage";
import ContainerForFormForInputUserInformation
  from "../../AdditionalComponents/FormForInputUserInfo/ContainerForFormForInputUserInformation";

export const LoginPage = (props) => {

  const {
    userDetails,
    dispatchUserState,
    sendFetchForLoginUser
  } = props;

  const styleForMainContainer = {
    backgroundColor: "white",
    margin: "0px",
    padding: "0px"
  };

  //Main HTML return
  return (
    <Container
      style={styleForMainContainer}
    >
      <AlertMessage
        show={userDetails.isError}
        onClose={() => {dispatchUserState({type: "CLOSE_ERROR_MASSAGE"})}}
        message="Wrong Login Details"
        type="error"
      />
      <ContainerForFormForInputUserInformation
        {...props}
        fetchRequest        ={(loginDetails) => {sendFetchForLoginUser(loginDetails)}}
        primaryLabel        ="Zaloguj"
        secondaryLabel      ="Log In"
        showEmailForm       ={true}
        showPasswordForm    ={true}
        showFirstNameForm   ={false}
        showLastNameForm    ={false}
        showLicenceForm     ={false}
        showPhotoURLForm    ={false}
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
    </Container>
  );
};

export default LoginPage
