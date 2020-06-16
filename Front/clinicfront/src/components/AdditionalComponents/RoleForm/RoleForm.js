import React from "react";
import {MenuItem} from "@material-ui/core";
import CustomTextField from "../CustomTextField";
import TextFieldCustomTypography from "../CustomTypography/TextFieldLabelCustomTypography";

export const RoleForm = (props) => {
    const {handleChange, role} = props;

    return (
        <CustomTextField
            fullWidth
            variant="outlined"
            label={
              <TextFieldCustomTypography
                primaryLabel={"Zarejstruj Jako"}
                secondaryLabel={"Register As"}
              />
            }
            select
            value={role}
            onChange={(event) => {
                handleChange(event.target.value)
            }}
        >
            <MenuItem value="doctor">
              <TextFieldCustomTypography
                primaryLabel={"Doktor"}
                secondaryLabel={"Doctor"}
              />
            </MenuItem>
            <MenuItem value="patient">
              <TextFieldCustomTypography
                primaryLabel={"Pacjent"}
                secondaryLabel={"Patient"}
              />
            </MenuItem>
            <MenuItem value="assistant">
              <TextFieldCustomTypography
                primaryLabel={"Asystentka"}
                secondaryLabel={"Assistant"}
              />
            </MenuItem>
        </CustomTextField>
    )
};