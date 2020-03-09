import {Col, Form} from "react-bootstrap";
import React from "react";
import {TextField} from "@material-ui/core";

export const FirstNameForm = (props) => (
    <Form.Group as={Col}>
        <TextField
            onChange={(e) => props.handleChange(e)}
            name="firstName"
            label="First Name"
            variant="outlined"
            defaultValue={(props.userInformation) ? (props.userInformation.firstName) : null}
        />
    </Form.Group>
);