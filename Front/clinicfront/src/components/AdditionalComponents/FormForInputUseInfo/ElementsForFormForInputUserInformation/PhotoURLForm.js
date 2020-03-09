import {Col, Form} from "react-bootstrap";
import React from "react";
import {TextField} from "@material-ui/core";

export const PhotoURLForm = (props) => (
    <Form.Group as={Col}>
        <TextField
            onChange={(e) => props.handleChange(e)}
            name="photoUrl"
            label="Photo URl"
            variant="outlined"
            defaultValue={(props.userInformation) ? (props.userInformation.photoUrl) : null}
        />
    </Form.Group>
);