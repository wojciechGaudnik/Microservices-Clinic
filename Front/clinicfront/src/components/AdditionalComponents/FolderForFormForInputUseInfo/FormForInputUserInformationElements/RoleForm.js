import {Col, Form} from "react-bootstrap";
import {styleForFormLabel} from "../../../../containers/SetFormForInputUserInformation";
import React from "react";

export const RoleForm = (props) => (
    <Form.Group as={Col}>
        <Form.Label style={styleForFormLabel}>Role</Form.Label>
        <Form.Control onChange={(e) => props.handleChange(e)}
                      placeholder="Role"
                      name="role"
                      as="select">
            <option value="doctor">doctor</option>
            <option value="patient">patient</option>
            <option value="assistant">assistant</option>
        </Form.Control>
    </Form.Group>
);