import {Col, Form} from "react-bootstrap";
import {TextField} from "@material-ui/core";
import React from "react";

export const PeselForm = (props) => (
    <Form.Group as={Col}>
        <TextField
            onChange={(e) => props.handleChange(e)}
            name="pesel"
            label="Pesel"
            variant="outlined"
            fullWidth
            defaultValue={(props.userInformation) ? (props.userInformation.pesel) : null}
        />
    </Form.Group>
);