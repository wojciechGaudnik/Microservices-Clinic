import React from "react";
import {MenuItem} from "@material-ui/core";
import CustomTextField from "../CustomTextField";

export const RoleForm = (props) => {
    const {handleChange, role} = props;

    return (
        <CustomTextField
            fullWidth
            variant="outlined"
            label="Register As"
            select
            value={role}
            onChange={(event) => {
                handleChange(event.target.value)
            }}
        >
            <MenuItem value="doctor">Doctor</MenuItem>
            <MenuItem value="patient">Patient</MenuItem>
            <MenuItem value="assistant">Assistant</MenuItem>
        </CustomTextField>
    )
};