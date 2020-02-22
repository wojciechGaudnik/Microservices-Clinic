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
            margin: '50px auto auto 50px',
            width: '30%',
        }}>
            <Form
                onSubmit={e => {
                    e.preventDefault();
                    getTokenByGivenLoginDetails(email.value, password.value, {setUserDetails})
                }}
                style={{
                    border: '2px solid white',
                    borderRadius: '5px',
                    padding: '8px'
                }}
            >
                <Form.Group controlId="formBasicEmail">
                    <Form.Label style={{color:'white'}}>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" name="email" ref={input => email = input}/>
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label style={{color:'white'}}>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" name="password" ref={input => password = input}/>
                </Form.Group>

                <Button variant="light" type="submit">
                    Log In
                </Button>
            </Form>
        </div>
    );
};

export default LoginPage
