import {Grid, Typography} from "@material-ui/core";
import {FormForInputUserInformation} from "../../../AdditionalComponents/FormForInputUserInfo/FormForInputUserInformation";
import React from "react";
import ContainerForFormForInputUserInformation
  from "../../../AdditionalComponents/FormForInputUserInfo/ContainerForFormForInputUserInformation";

export const EditDataFormComponent = (props) => {
  //TODO: Trzeba koniecznie dopracować edytowanie w formie

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
            {...props}
            submitButtonTitle   ="Edit"
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
                submitButtonTitle,
                submitButtonAvailable,
                validation,
                handleChange,
                setIsCorrectInputInForms,
                props
              }) => (
              <FormForInputUserInformation
                onSubmit={onSubmit}
                submitButtonTitle={submitButtonTitle}
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
                props={props}
              />
            )}
          </ContainerForFormForInputUserInformation>
        </Grid>
    )
};

export default EditDataFormComponent