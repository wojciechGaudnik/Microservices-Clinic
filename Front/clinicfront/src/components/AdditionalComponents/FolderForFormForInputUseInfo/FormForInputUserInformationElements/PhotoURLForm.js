import {Col, Form} from "react-bootstrap";
import {styleForFormLabel} from "../../../../containers/SetFormForInputUserInformation";
import React from "react";

export const PhotoURLForm = (props) => (
    <Form.Group as={Col}>
        <Form.Label style={styleForFormLabel}>PhotoURL</Form.Label>
        <Form.Control onChange={(e) => props.handleChange(e)}
                      placeholder="PhotoURl"
                      name="photoUrl"
                      defaultValue={(props.userInformation) ? (props.userInformation.photoUrl) : null}
        />
    </Form.Group>
);