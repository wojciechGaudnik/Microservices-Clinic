import {Col, Form} from "react-bootstrap";
import {styleForFormLabel} from "../../../../containers/SetFormForInputUserInformation";
import React from "react";

export const PasswordForm = (props) => (
    <Form.Group as={Col} controlId="formGridPassword">
        <Form.Label style={styleForFormLabel}>Password</Form.Label>
        <Form.Control type="password"
                      onChange={(e) => props.handleChange(e)}
                      placeholder="Password"
                      name="password"
        />
    </Form.Group>
);