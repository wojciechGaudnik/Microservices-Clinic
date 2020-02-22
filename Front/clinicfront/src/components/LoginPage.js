import React, {useEffect, useState} from "react";
import {getTokenByGivenLoginDetails, redirectByRole} from "../actions";
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form'

export const LoginPage = (props) => {
    const [userDetails, setUserDetails] = useState({
        uuid: null,
        role: null
    });

    let email;
    let password;

    useEffect(() => {
        props.setStoreUserDetails(userDetails);
        if (userDetails.role){
            redirectByRole(userDetails.role, props)
        }
    }, [userDetails]);

    return (
        <div style={{
            padding: '40px',
            width: '30%',
        }}>
            <Form onSubmit={e => {
                e.preventDefault();
                getTokenByGivenLoginDetails(email.value, password.value, {setUserDetails})
            }}>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" name="email" ref={input => email = input}/>
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" name="password" ref={input => password = input}/>
                </Form.Group>

                <Button variant="outline-primary" type="submit">
                    Log In
                </Button>
            </Form>
        </div>
    );
};

export default LoginPage
