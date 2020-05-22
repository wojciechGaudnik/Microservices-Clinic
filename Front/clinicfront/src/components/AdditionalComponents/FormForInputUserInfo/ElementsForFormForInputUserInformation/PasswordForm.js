import {Col, Form} from "react-bootstrap";
import React, {useState} from "react";
import CustomTextField from "../../CustomTextField";

export const PasswordForm = (props) => {
    const { handleChange, validation, setIsCorrectInputInForms } = props;

    const [isCorrectInput, setIsCorrectInput] = useState(true);
    const [messageForIncorrectInput, setMessageForIncorrectInput] = useState(null);

    const setGoodInputInAllStates = () => {
        setIsCorrectInput(true);
        setIsCorrectInputInForms({passwordForm: true});
    };

    const setWrongInputInAllStates = () => {
        setIsCorrectInput(false);
        setIsCorrectInputInForms({passwordForm: false});
    };

    //Validation for input data
    const checkInputCorrect = (e) => {
        if (e.target.value.length < 9 || e.target.value.length > 16){
            setWrongInputInAllStates();
            setMessageForIncorrectInput("Must contain between 8 and 16 characters")
        } else {
            setGoodInputInAllStates();
            setMessageForIncorrectInput(null);
        }
    };

    return (
        <Form.Group as={Col} controlId="formGridPassword">
            <CustomTextField
                onChange={(e) => {
                    handleChange(e);
                    if (validation){checkInputCorrect(e)}
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