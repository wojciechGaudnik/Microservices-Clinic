import {Col, Form} from "react-bootstrap";
import {styleForFormLabel} from "../../../../containers/SetFormForInputUserInformation";
import React from "react";

export const EmailForm = (props) => (
    <Form.Group as={Col} controlId="formGridEmail">
        <Form.Label style={styleForFormLabel}>Email</Form.Label>
        <Form.Control type="email"
                      onChange={(e) => props.handleChange(e)}
                      placeholder="Email"
                      name="email"
        />
    </Form.Group>
);