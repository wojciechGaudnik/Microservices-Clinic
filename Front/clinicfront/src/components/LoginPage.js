import React, {useEffect, useState} from "react";
import {getTokenByGivenLoginDetails, redirectByRole} from "../actions";
import {Button, Form, OverlayTrigger, Popover, PopoverContent} from "react-bootstrap";

export const LoginPage = (props) => {
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

    //PopOver
    const validEmailRegex = RegExp(/^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i);

    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);

    const handleChange = (event) => {
        const { name, value} = event.target;
        switch (name) {
            case 'password':
                setPassword(value);
                return;
            case 'email':
                setEmail(value);
                return;
        }
    };

    const popover = (input) => {
        if (input === null){
            return (
                <Popover id="popover-basic">
                    <PopoverContent>
                        Fill it!
                    </PopoverContent>
                </Popover>)
        } else {
            return (
                <Popover id="popover-basic">
                    <PopoverContent>
                        {input}
                    </PopoverContent>
                </Popover>)
        }
    };

    //CSS stylesheet
    const styleForFormLabel = {color:'white'};

    const styleForForm = {
        border: '2px solid white',
        borderRadius: '5px',
        padding: '8px'
    };

    const styleForMainDiv = {
        margin: '50px auto auto 50px',
        width: '30%',
    };

    //Main HTML return
    return (
        <div style={styleForMainDiv}>
            <Form
                onSubmit={e => {
                    e.preventDefault();
                    getTokenByGivenLoginDetails(email, password, {setUserDetails})
                }}
                style={styleForForm}
            >
                <Form.Group controlId="formBasicEmail">
                    <Form.Label style={styleForFormLabel}>Email address</Form.Label>
                    <OverlayTrigger
                        overlay={popover(email)}
                        trigger="focus"
                        placement="right"
                    >
                        <Form.Control
                            type="email"
                            onChange={(e) => handleChange(e)}
                            placeholder="Enter email"
                            name="email"
                        />
                    </OverlayTrigger>
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label style={styleForFormLabel}>Password</Form.Label>
                    <OverlayTrigger
                        overlay={popover(password)}
                        trigger="focus"
                        placement="right"
                    >
                        <Form.Control
                            type="password"
                            onChange={(e) => handleChange(e)}
                            placeholder="Password"
                            name="password"/>
                    </OverlayTrigger>
                </Form.Group>

                <Button variant="light" type="submit">
                    Log In
                </Button>
            </Form>
        </div>
    );
};

export default LoginPage
