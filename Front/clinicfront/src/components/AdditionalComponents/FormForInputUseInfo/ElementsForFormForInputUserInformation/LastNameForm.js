import {Col, Form} from "react-bootstrap";
import React, {useState} from "react";
import {TextField} from "@material-ui/core";

export const LastNameForm = (props) => {
    const { handleChange, validation, userInformation } = props;

    const [isCorrectInput, setIsCorrectInput] = useState(true);
    const [messageForIncorrectInput, setMessageForIncorrectInput] = useState(null);

    //Validation for input data
    const checkInputCorrect = (e) => {
        if (e.target.value.length === 0){
            setIsCorrectInput(false);
            setMessageForIncorrectInput("The field cannot be empty")
        } else {
            setIsCorrectInput(true);
            setMessageForIncorrectInput(null);
        }
    };

    return (
        <Form.Group as={Col}>
            <TextField
                onChange={(e) => {
                    handleChange(e);
                    if (validation){checkInputCorrect(e)}
                }}
                name="lastName"
                label="Last Name"
                variant="outlined"
                error={!isCorrectInput}
                helperText={messageForIncorrectInput}
                fullWidth
                defaultValue={(userInformation) ? (userInformation.lastName) : null}
            />
        </Form.Group>)
};