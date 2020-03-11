import {Col, Form} from "react-bootstrap";
import React, {useState} from "react";
import {TextField} from "@material-ui/core";

export const EmailForm = (props) => {
    const { handleChange, validation} = props;

    const [isCorrectInput, setIsCorrectInput] = useState(true);
    const [messageForIncorrectInput, setMessageForIncorrectInput] = useState(null);

    const emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    //Validation for input data
    const checkInputCorrect = (e) => {
        if (e.target.value.length === 0){
            setIsCorrectInput(false);
            setMessageForIncorrectInput("The field cannot be empty")
        } else if (!e.target.value.match(emailFormat)){
            setIsCorrectInput(false);
            setMessageForIncorrectInput("The text in field is not email")
        } else {
            setIsCorrectInput(true);
            setMessageForIncorrectInput(null);
        }
    };

    return (
        <Form.Group as={Col} controlId="formGridEmail">
            <TextField
                onChange={(e) => {
                    handleChange(e);
                    if (validation){checkInputCorrect(e)}
                }}
                name="email"
                label="Email"
                variant="outlined"
                error={!isCorrectInput}
                helperText={messageForIncorrectInput}
                fullWidth
            />
        </Form.Group>)
};