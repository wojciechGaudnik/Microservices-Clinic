import {Col, Form} from "react-bootstrap";
import React, {useState} from "react";
import {TextField} from "@material-ui/core";

export const LicenceForm = (props) => {
    const { handleChange, validation, userInformation, setIsCorrectInputInForms } = props;

    const [isCorrectInput, setIsCorrectInput] = useState(true);
    const [messageForIncorrectInput, setMessageForIncorrectInput] = useState(null);

    const setGoodInputInAllStates = () => {
        setIsCorrectInput(true);
        setIsCorrectInputInForms({licenceForm: true});
    };

    const setWrongInputInAllStates = () => {
        setIsCorrectInput(false);
        setIsCorrectInputInForms({licenceForm: false});
    };

    //Validation for input data
    const checkInputCorrect = (e) => {
        if (e.target.value.length === 0){
            setWrongInputInAllStates();
            setMessageForIncorrectInput("The field cannot be empty")
        } else {
            setGoodInputInAllStates();
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
                name="licence"
                label="Licence"
                variant="outlined"
                error={!isCorrectInput}
                helperText={messageForIncorrectInput}
                fullWidth
                defaultValue={(userInformation) ? (userInformation.licence) : null}
            />
        </Form.Group>)
};