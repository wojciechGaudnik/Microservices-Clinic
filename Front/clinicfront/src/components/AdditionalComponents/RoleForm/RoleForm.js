import React from "react";
import {FormControl, InputLabel, Select, MenuItem, TextField} from "@material-ui/core";

export const RoleForm = (props) => {
    const { handleChange, role } = props;

    return(
        // <FormControl variant="outlined" fullWidth>
            <TextField
                fullWidth
                variant="outlined"
                label="Register As"
                select
                value={role}
                onChange={(event) => {handleChange(event.target.value)}}
            >
                <MenuItem value="doctor">Doctor</MenuItem>
                <MenuItem value="patient">Patient</MenuItem>
                <MenuItem value="assistant">Assistant</MenuItem>
            </TextField>
        // </FormControl>
    )
};