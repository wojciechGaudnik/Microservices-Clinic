import {Col, Form} from "react-bootstrap";
import React, {useState} from "react";
import {TextField} from "@material-ui/core";

export const PhotoURLForm = (props) => {
    const { handleChange, validation, userInformation, setIsCorrectInputInForms} = props;

    const [isCorrectInput, setIsCorrectInput] = useState(true);
    const [messageForIncorrectInput, setMessageForIncorrectInput] = useState(null);

    const setGoodInputInAllStates = () => {
        setIsCorrectInput(true);
        setIsCorrectInputInForms({photoURLForm: true});
    };

    const setWrongInputInAllStates = () => {
        setIsCorrectInput(false);
        setIsCorrectInputInForms({photoURLForm: false});
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
                name="photoUrl"
                label="Photo URL"
                variant="outlined"
                error={!isCorrectInput}
                helperText={messageForIncorrectInput}
                fullWidth
                defaultValue={(userInformation) ? (userInformation.photoUrl) : null}
            />
        </Form.Group>)
};