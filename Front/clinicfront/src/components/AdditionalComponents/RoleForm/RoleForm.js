import React from "react";
import {MenuItem} from "@material-ui/core";
import StylesTextField from "../CustomTextField/StylesTextField";
import TextFieldCustomTypography from "../CustomTypography/TypesOfCustomTypography/TextFieldLabelCustomTypography";

export const RoleForm = (props) => {
    const {handleChange, role} = props;

    return (
        <StylesTextField
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
        </StylesTextField>
    )
};