import React from "react";
import {FormForInputUserInformation} from "../../AdditionalComponents/FormForInputUserInfo/FormForInputUserInformation";
import AlertMessage from "../../AdditionalComponents/Alert/AlertMessage";
import {Container, Grid} from "@material-ui/core";
import {RoleForm} from "../../AdditionalComponents/RoleForm/RoleForm";

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
          <FormForInputUserInformation
            {...props}
            fetchRequest        ={(registerDetails) => fetchRegisterNewUser(registerDetails)}
            role                ={registerStatus.roleNewUser}
            submitButtonTitle   ="Register"
            showEmailForm       ={true}
            showPasswordForm    ={true}
            showFirstNameForm   ={true}
            showLastNameForm    ={true}
            showLicenceForm     ={true}
            showPhotoURLForm    ={true}
          />
        );
      case "patient":
        return(
          <FormForInputUserInformation
            {...props}
            fetchRequest        ={(registerDetails) => fetchRegisterNewUser(registerDetails)}
            role                ={registerStatus.roleNewUser}
            submitButtonTitle   ="Register"
            showEmailForm       ={true}
            showPasswordForm    ={true}
            showFirstNameForm   ={true}
            showLastNameForm    ={true}
            showPhotoURLForm    ={true}
            showPeselForm       ={true}
          />
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