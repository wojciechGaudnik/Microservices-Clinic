import {Col, Form} from "react-bootstrap";
import React, {useState} from "react";
import StylesTextField from "../../CustomTextField/StylesTextField";
import TextFieldCustomTypography from "../../CustomTypography/TypesOfCustomTypography/TextFieldLabelCustomTypography";

export const PeselForm = (props) => {
    const { handleChange, validation, userInformation, setIsCorrectInputInForms } = props;

    const [isCorrectInput, setIsCorrectInput] = useState(true);
    const [messageForIncorrectInput, setMessageForIncorrectInput] = useState(null);

    const setGoodInputInAllStates = () => {
        setIsCorrectInput(true);
        setIsCorrectInputInForms({peselForm: true});
    };

    const setWrongInputInAllStates = () => {
        setIsCorrectInput(false);
        setIsCorrectInputInForms({peselForm: false});
    };

    //Validation for input data
    const checkInputCorrect = (e) => {
        if (e.target.value.length === 0){
            setWrongInputInAllStates();
            setMessageForIncorrectInput("The field cannot be empty")
        } else if (e.target.value.length !== 11){
            setWrongInputInAllStates();
            setMessageForIncorrectInput("Pesel contain 11 numbers")
        } else {
            setGoodInputInAllStates();
            setMessageForIncorrectInput(null);
        }
    };

    return (
        <Form.Group as={Col}>
            <StylesTextField
                onChange={(e) => {
                    handleChange({pesel : e.target.value});
                    if (validation){checkInputCorrect(e)}
                }}
                name="pesel"
                label={
                    <TextFieldCustomTypography
                      primaryLabel={"Pesel"}
                      secondaryLabel={"Pesel"}
                    />
                }
                variant="outlined"
                error={!isCorrectInput}
                helperText={messageForIncorrectInput}
                fullWidth
                defaultValue={(userInformation) ? (userInformation.pesel) : null}
            />
        </Form.Group>)
};