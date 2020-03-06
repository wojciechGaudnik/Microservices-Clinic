import {Col, Form} from "react-bootstrap";
import {styleForFormLabel} from "../../../../containers/SetFormForInputUserInformation";
import React from "react";

export const FirstNameForm = (props) => (
    <Form.Group as={Col}>
        <Form.Label style={styleForFormLabel}>First Name</Form.Label>
        <Form.Control onChange={(e) => props.handleChange(e)}
                      placeholder="firstName"
                      name="firstName"
                      defaultValue={(props.userInformation) ? (props.userInformation.firstName) : null}
        />
    </Form.Group>
);