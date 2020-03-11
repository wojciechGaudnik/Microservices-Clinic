import {Col, Form} from "react-bootstrap";
import React, {useState} from "react";
import {TextField} from "@material-ui/core";

export const PasswordForm = (props) => {
    const { handleChange, validation } = props;

    const [isCorrectInput, setIsCorrectInput] = useState(true);
    const [messageForIncorrectInput, setMessageForIncorrectInput] = useState(null);

    //Validation for input data
    const checkInputCorrect = (e) => {
        if (e.target.value.length < 8 || e.target.value.length > 16){
            setIsCorrectInput(false);
            setMessageForIncorrectInput("Must contain between 8 and 16 characters")
        }
        else {
            setIsCorrectInput(true);
            setMessageForIncorrectInput(null);
        }
    };

    return (<Form.Group as={Col} controlId="formGridPassword">
        <TextField
            onChange={(e) => {
                handleChange(e);
                if (validation){checkInputCorrect(e);}
            }}
            name="password"
            type="password"
            label="Password"
            variant="outlined"
            error={!isCorrectInput}
            helperText={messageForIncorrectInput}
            fullWidth
        />
    </Form.Group>)
};