import {Col, Form} from "react-bootstrap";
import React from "react";
import {TextField} from "@material-ui/core";

export const LicenceForm = (props) => (
    <Form.Group as={Col}>
        <TextField
            onChange={(e) => props.handleChange(e)}
            name="licence"
            label="Licence"
            variant="outlined"
            fullWidth
            defaultValue={(props.userInformation) ? (props.userInformation.licence) : null}
        />
    </Form.Group>
);