import React, {useEffect, useState} from "react";

import {redirectByRole, useFormFields} from "../actions";

import {Button, Form} from "react-bootstrap";

import {ErrorModal} from "./AdditionalComponents/ErrorModal";

import {
    sendFetchRequestLoginUser, sendFetchRequestIsThereLoginUser,
    styleForForm, styleForFormLabel, styleForMainDiv, styleForButton
} from "../containers/SetLoginPage";

export const LoginPage = (props) => {
    const [loginDetails, setLoginDetails] = useFormFields({
        email: "",
        password: ""
    });
    const [userDetails, setUserDetails] = useState({
        uuid: null,
        role: null
    });

    //Effects after each render
    useEffect(() => {
        if (localStorage.token && !userDetails.role){sendFetchRequestIsThereLoginUser({setUserDetails}); console.log("Works")}
    }, []);
    useEffect(() => {
        props.setStoreUserDetails(userDetails);
        if (userDetails.role){redirectByRole(userDetails.role, props)}
    }, [userDetails]);

    //Handle change
    const handleChange = (event) => {
        setLoginDetails(event)
    };

    //Main HTML return
    return (
        <div style={styleForMainDiv}>

            {/*Modal for fetch error*/}
            {props.error ? (<ErrorModal/>) : null}

            <Form
                onSubmit={e => {
                    e.preventDefault();
                    sendFetchRequestLoginUser(loginDetails, {setUserDetails}, {ifCatchSetErrorInStore: (error) => {props.setStoreError(error)}});
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
