import {Col, Form} from "react-bootstrap";
import {styleForFormLabel} from "../Containers/SetFormForInputUserInformation";
import React from "react";

export const LicenceForm = (props) => (
    <Form.Group as={Col}>
        <Form.Label style={styleForFormLabel}>Licence</Form.Label>
        <Form.Control onChange={(e) => props.handleChange(e)}
                      placeholder="Licence"
                      name="licence"
                      defaultValue={(props.userInformation) ? (props.userInformation.licence) : null}
        />
    </Form.Group>
);