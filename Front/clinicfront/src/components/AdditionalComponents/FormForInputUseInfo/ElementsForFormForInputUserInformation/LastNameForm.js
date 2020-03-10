import {Col, Form} from "react-bootstrap";
import React from "react";
import {TextField} from "@material-ui/core";

export const LastNameForm = (props) => (
    <Form.Group as={Col}>
        <TextField
            onChange={(e) => props.handleChange(e)}
            name="lastName"
            label="Last Name"
            variant="outlined"
            fullWidth
            defaultValue={(props.userInformation) ? (props.userInformation.lastName) : null}
        />
    </Form.Group>
);