import React, {useEffect, useState} from "react";

import {redirectByRole, useFormFields} from "../../actions";

import {Button, Col, Form} from "react-bootstrap";
import {styleForForm, styleForFormLabel} from "../../containers/SetFormForInputUserInformation";


export const FormForInputUserInformation = (props) => {
    const [isFetchResponseOk, setIsFetchResponseOk] = useState(false);
    const [userInformation, setUserInformation] = useFormFields({
        firstName:  null,
        lastName:   null,
        licence:    null,
        photoURL:   null,
        email:      null,
        password:   null,
        role:       "doctor"
    });

    const handleChange = (event) => {
        setUserInformation(event);
    };

    let sendFetchRequest = () => {
        const ifCatchSetErrorInStore = (error) => {props.setStoreError(error)};
        switch (props.variant) {
            case "register":
                props.fetchRequest(userInformation, {ifCatchSetErrorInStore});
                break;
            case "edit":
                console.log(props.userDetails);
                props.fetchRequest(userInformation, {ifCatchSetErrorInStore}, {uuid: props.userDetails.uuid});
                break;
        }
    };

    const whichFormVariant = () => {

    };

    useEffect(() => {
        whichFormVariant();
        if (!props.error && isFetchResponseOk){
            redirectByRole(null, props);
        }
    }, [isFetchResponseOk]);

    return (
        <Form
            onSubmit={e => {
                e.preventDefault();
                sendFetchRequest();
                setTimeout(() => {setIsFetchResponseOk(true)}, 1000);
            }}
            style={styleForForm}
        >
            <Form.Row>
                {props.showEmailForm ? (
                    <Form.Group as={Col} controlId="formGridEmail">
                        <Form.Label style={styleForFormLabel}>Email</Form.Label>
                        <Form.Control type="email"
                                      onChange={(e) => handleChange(e)}
                                      placeholder="Email"
                                      name="email"
                        />
                    </Form.Group>
                ) : null}

                {props.showPasswordForm ? (
                    <Form.Group as={Col} controlId="formGridPassword">
                        <Form.Label style={styleForFormLabel}>Password</Form.Label>
                        <Form.Control type="password"
                                      onChange={(e) => handleChange(e)}
                                      placeholder="Password"
                                      name="password"/>
                    </Form.Group>
                ) : null}
            </Form.Row>
            <Form.Row>
                {props.showRoleForm ? (
                    <Form.Group as={Col}>
                        <Form.Label style={styleForFormLabel}>Role</Form.Label>
                        <Form.Control onChange={(e) => handleChange(e)}
                                      placeholder="Role"
                                      name="role"
                                      as="select">
                            <option value="doctor">doctor</option>
                            <option value="patient">patient</option>
                            <option value="assistant">assistant</option>
                        </Form.Control>
                    </Form.Group>
                ) : null}

                {props.showFirstNameForm ? (
                    <Form.Group as={Col}>
                        <Form.Label style={styleForFormLabel}>First Name</Form.Label>
                        <Form.Control onChange={(e) => handleChange(e)}
                                      placeholder="firstName"
                                      name="firstName"
                                      />
                    </Form.Group>
                ) : null}

                {props.showLastNameForm ? (
                    <Form.Group as={Col}>
                        <Form.Label style={styleForFormLabel}>Last Name</Form.Label>
                        <Form.Control onChange={(e) => handleChange(e)}
                                      placeholder="lastName"
                                      name="lastName"
                                      />
                    </Form.Group>
                ) : null}
            </Form.Row>
            <Form.Row>
                {props.showLicenceForm ? (
                    <Form.Group as={Col}>
                        <Form.Label style={styleForFormLabel}>Licence</Form.Label>
                        <Form.Control onChange={(e) => handleChange(e)}
                                      placeholder="Licence"
                                      name="licence"
                                      />
                    </Form.Group>
                ) : null}

                {props.showPhotoURLForm ? (
                    <Form.Group as={Col}>
                        <Form.Label style={styleForFormLabel}>PhotoURL</Form.Label>
                        <Form.Control onChange={(e) => handleChange(e)}
                                      placeholder="PhotoURL"
                                      name="photoURL"
                                      />
                    </Form.Group>
                ) : null}
            </Form.Row>
            <Button variant="light" type="submit">
                {props.submitButtonTitle}
            </Button>
        </Form>
    );
};