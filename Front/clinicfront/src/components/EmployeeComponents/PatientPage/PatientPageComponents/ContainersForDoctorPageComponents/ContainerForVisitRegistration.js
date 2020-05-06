import {URLs} from "../../../../../URLs";
import {sendRequestByGivenDetails} from "../../../../../actions/fetchRequest";
import {MenuItem, TextField} from "@material-ui/core";
import React from "react";

export const WhichDoctorForm = (props) => {
    const {handleChange, whichDoctor, allDoctors} = props;

    return (
        <TextField
            fullWidth
            variant="outlined"
            label="Select Doctor"
            select
            value={whichDoctor}
            onChange={(event) => handleChange(event)}
        >
            {allDoctors.map(doctor => (<MenuItem value={doctor}>{doctor.firstName} {doctor.lastName}</MenuItem>))}
        </TextField>
    )
};

export const sendFetchRequestSetAllDoctors = () => {
    const URL = URLs.GET_ALL_DOCTORS;

    const body = null;

    const method = 'GET';

    const headers = {'Content-Type': 'application/json',};

    const setInStateFunction = null;

    const specialFunction = (responseData) => console.log(responseData);

    sendRequestByGivenDetails(
        URL,
        body,
        method,
        headers,
        setInStateFunction,
        specialFunction
    )
};