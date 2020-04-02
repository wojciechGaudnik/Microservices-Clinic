import {Grid, Typography} from "@material-ui/core";
import {FormForInputUserInformation} from "../../../AdditionalComponents/FormForInputUserInfo/FormForInputUserInformation";
import React from "react";

export const EditDataFormComponent = (props) => {
    return(
        <Grid item>
            <Typography
                color="primary"
                align="center"
                variant="subtitle2"
                gutterBottom={true}
            >
                Fill or change only variables which you want to change
            </Typography>
            <FormForInputUserInformation
                {...props}
                submitButtonTitle   ="Edit"
                showPeselForm    ={true}
                showFirstNameForm   ={true}
                showLastNameForm    ={true}
                showPhotoURLForm    ={true}
            />
        </Grid>
    )
};

export default EditDataFormComponent