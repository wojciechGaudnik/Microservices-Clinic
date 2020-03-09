import {Col, Form} from "react-bootstrap";
import React from "react";
import {TextField} from "@material-ui/core";

export const PasswordForm = (props) => (
    <Form.Group as={Col} controlId="formGridPassword">
        <TextField
            onChange={(e) => props.handleChange(e)}
            name="password"
            label="Password"
            variant="outlined"
        />
    </Form.Group>
);