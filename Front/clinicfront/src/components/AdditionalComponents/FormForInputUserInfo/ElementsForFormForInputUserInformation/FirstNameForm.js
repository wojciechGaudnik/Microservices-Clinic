import {Col, Form} from "react-bootstrap";
import React, {useState} from "react";
import CustomTextField from "../../CustomTextField";
import TextFieldCustomTypography from "../../CustomTypography/TextFieldLabelCustomTypography";

export const FirstNameForm = (props) => {
    const { handleChange, validation, setIsCorrectInputInForms, userInformation } = props;

    const [isCorrectInput, setIsCorrectInput] = useState(true);
    const [messageForIncorrectInput, setMessageForIncorrectInput] = useState(null);

    const setGoodInputInAllStates = () => {
        setIsCorrectInput(true);
        setIsCorrectInputInForms({firstNameForm: true});
    };

    const setWrongInputInAllStates = () => {
        setIsCorrectInput(false);
        setIsCorrectInputInForms({firstNameForm: false});
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
            <CustomTextField
                onChange={(e) => {
                    handleChange({firstName : e.target.value});
                    if (validation){checkInputCorrect(e)}
                }}
                name="firstName"
                label={
                    <TextFieldCustomTypography
                      primaryLabel={"Imię"}
                      secondaryLabel={"First Name"}
                    />
                }
                variant="outlined"
                error={!isCorrectInput}
                helperText={messageForIncorrectInput}
                fullWidth
                defaultValue={(userInformation) ? (userInformation.firstName) : null}
            />
        </Form.Group>)
};