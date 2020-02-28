import React, {useEffect, useState} from "react";
import {redirectByRole} from "../actions";
import {Button, Form} from "react-bootstrap";

import {
    sendFetchRequestLoginUser,
    styleForMainDiv, styleForForm, styleForFormLabel
} from "../containers/SetLoginPage";
import {FormForInputUserInformation} from "./FormForInputUserInformation";


export const LoginPage = (props) => {
    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);
    const [userDetails, setUserDetails] = useState({
        uuid: null,
        role: null
    });

    //Effects after each render
    useEffect(() => {
        props.setStoreUserDetails(userDetails);
        if (userDetails.role){
            redirectByRole(userDetails.role, props)
        }
    }, [userDetails]);

    //Handle change
    const handleChange = (event) => {
        const {name, value} = event.target;
        switch (name) {
            case 'password':
                setPassword(value);
                return;
            case 'email':
                setEmail(value);
                return;
        }
    };

    //Main HTML return
    return (
        <div style={styleForMainDiv}>
            <Form
                onSubmit={e => {
                    e.preventDefault();
                    sendFetchRequestLoginUser(email, password, {setUserDetails})
                }}
                style={styleForForm}
            >
                <Form.Group controlId="formBasicEmail">
                    <Form.Label style={styleForFormLabel}>Email address</Form.Label>
                    <Form.Control
                        type="email"
                        onChange={(e) => handleChange(e)}
                        placeholder="Enter email"
                        name="email"
                    />
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label style={styleForFormLabel}>Password</Form.Label>
                    <Form.Control
                        type="password"
                        onChange={(e) => handleChange(e)}
                        placeholder="Password"
                        name="password"/>
                </Form.Group>
                <Button variant="light" type="submit">
                    Log In
                </Button>
            </Form>
        </div>
    );
};

export default LoginPage
