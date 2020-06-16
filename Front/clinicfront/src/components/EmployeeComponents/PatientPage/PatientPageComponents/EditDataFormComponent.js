import {Grid, Typography} from "@material-ui/core";
import {FormForInputUserInformation} from "../../../AdditionalComponents/FormForInputUserInfo/FormForInputUserInformation";
import React from "react";
import ContainerForFormForInputUserInformation
  from "../../../AdditionalComponents/FormForInputUserInfo/ContainerForFormForInputUserInformation";

export const EditDataFormComponent = (props) => {
  const {
    userInformation,
    fetchRequest
  } = props;

    return(
        <Grid item>
            <Typography
                align="center"
                variant="subtitle2"
                gutterBottom={true}
            >
                Fill or change only variables which you want to change
            </Typography>
          <ContainerForFormForInputUserInformation
            userInformation     ={userInformation}
            fetchRequest        ={fetchRequest}
            primaryLabel        ="Edytuj"
            secondaryLabel      ="Edit"
            showEmailForm       ={true}
            showPasswordForm    ={true}
            showRoleForm        ={false}
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
                setIsCorrectInputInForms,
                userInformation
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
                userInformation={userInformation}
              />
            )}
          </ContainerForFormForInputUserInformation>
        </Grid>
    )
};

export default EditDataFormComponent