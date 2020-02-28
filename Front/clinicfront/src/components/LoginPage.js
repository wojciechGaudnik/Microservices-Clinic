import React, {useEffect, useState} from "react";

import {checkIsThereError, redirectByRole, useFormFields} from "../actions";

import {Button, Form} from "react-bootstrap";

import {sendFetchRequestLoginUser,
    styleForForm, styleForFormLabel, styleForMainDiv, styleForButton
} from "../containers/SetLoginPage";
import {ErrorModal} from "./ErrorModal";


export const LoginPage = (props) => {
    const [loginDetails, setLoginDetails] = useFormFields(null);
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
        setLoginDetails(event)
    };

    //Main HTML return
    return (
        <div style={styleForMainDiv}>
            {props.error ? (<ErrorModal/>) : null}
            <Form
                onSubmit={e => {
                    e.preventDefault();
                    sendFetchRequestLoginUser(loginDetails, {setUserDetails});
                    checkIsThereError(props);
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
                <Button variant="light" style={styleForButton} type="submit">
                    Log In
                </Button>
                <Button variant="light" style={styleForButton} onClick={() => redirectByRole("register", props)}>
                    Register
                </Button>
            </Form>
        </div>
    );
};

export default LoginPage
