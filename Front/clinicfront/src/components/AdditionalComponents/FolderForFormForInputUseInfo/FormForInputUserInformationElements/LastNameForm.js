import {Col, Form} from "react-bootstrap";
import {styleForFormLabel} from "../../../../containers/SetFormForInputUserInformation";
import React from "react";

export const LastNameForm = (props) => (
    <Form.Group as={Col}>
        <Form.Label style={styleForFormLabel}>Last Name</Form.Label>
        <Form.Control onChange={(e) => props.handleChange(e)}
                      placeholder="lastName"
                      name="lastName"
                      defaultValue={(props.userInformation) ? (props.userInformation.lastName) : null}
        />
    </Form.Group>
);