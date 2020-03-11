import {Col, Form} from "react-bootstrap";
import React from "react";
import {TextField} from "@material-ui/core";

export const EmailForm = (props) => (
    <Form.Group as={Col} controlId="formGridEmail">
        <TextField
            onChange={(e) => props.handleChange(e)}
            name="email"
            type="email"
            label="Email"
            variant="outlined"
            fullWidth
        />
    </Form.Group>
);