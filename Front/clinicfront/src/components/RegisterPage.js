import React from "react";
import {Button, Col, Form} from "react-bootstrap";
import {redirectByRole, useFormFields} from "../actions";

import {
    sendFetchRequestRegisterNewDoctor,
    styleForMainDiv, styleForForm, styleForFormLabel
} from "../containers/SetRegisterPage";


export const RegisterPage = (props) => {
    const [userRegisterInformation, setUserRegisterInformation] = useFormFields({
        firstName:  "",
        lastName:   "",
        licence:    "",
        photoURL:   "",
        email:      "",
        password:   "",
        role:       "doctor"
    });

    const handleChange = (event) => {
        console.log(event.target.value);
        setUserRegisterInformation(event);
    };

    //Main HTML return
    return(
        <div style={styleForMainDiv}>
            <Form
                onSubmit={e => {
                    e.preventDefault();
                    sendFetchRequestRegisterNewDoctor(userRegisterInformation);
                    redirectByRole(null, props)
                }}
                style={styleForForm}
            >
                <Form.Row>
                    <Form.Group as={Col} controlId="formGridEmail">
                        <Form.Label style={styleForFormLabel} >Email</Form.Label>
                        <Form.Control type="email"
                                      onChange={(e) => handleChange(e)}
                                      placeholder="Email"
                                      name="email" />
                    </Form.Group>
                    <Form.Group as={Col} controlId="formGridPassword">
                        <Form.Label style={styleForFormLabel} >Password</Form.Label>
                        <Form.Control type="password"
                                      onChange={(e) => handleChange(e)}
                                      placeholder="Password"
                                      name="password" />
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col}>
                        <Form.Label style={styleForFormLabel} >Role</Form.Label>
                        <Form.Control onChange={(e) => handleChange(e)}
                                      placeholder="Role"
                                      name="role"
                                      as="select">
                            <option value="doctor">doctor</option>
                            <option value="patient">patient</option>
                            <option value="assistant">assistant</option>
                        </Form.Control>
                    </Form.Group>
                    <Form.Group as={Col}>
                        <Form.Label style={styleForFormLabel} >First Name</Form.Label>
                        <Form.Control onChange={(e) => handleChange(e)}
                                      placeholder="firstName"
                                      name="firstName" />
                    </Form.Group>
                    <Form.Group as={Col}>
                        <Form.Label style={styleForFormLabel} >Last Name</Form.Label>
                        <Form.Control onChange={(e) => handleChange(e)}
                                      placeholder="lastName"
                                      name="lastName" />
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} >
                        <Form.Label style={styleForFormLabel} >Licence</Form.Label>
                        <Form.Control onChange={(e) => handleChange(e)}
                                      placeholder="Licence"
                                      name="licence" />
                    </Form.Group>
                    <Form.Group as={Col} >
                        <Form.Label style={styleForFormLabel} >PhotoURL</Form.Label>
                        <Form.Control onChange={(e) => handleChange(e)}
                                      placeholder="PhotoURL"
                                      name="photoURL" />
                    </Form.Group>
                </Form.Row>
                <Button variant="light" type="submit">
                    Register
                </Button>
            </Form>
        </div>
)};

export default RegisterPage