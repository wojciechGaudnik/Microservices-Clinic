import {Col, Form} from "react-bootstrap";
import React, {useState} from "react";
import StylesTextField from "../../CustomTextField/StylesTextField";
import TextFieldCustomTypography from "../../CustomTypography/TypesOfCustomTypography/TextFieldLabelCustomTypography";

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
            <StylesTextField
                onChange={(e) => {
                    handleChange({licence : e.target.value});
                    if (validation){checkInputCorrect(e)}
                }}
                name="licence"
                label={
                    <TextFieldCustomTypography
                      primaryLabel={"Licencja"}
                      secondaryLabel={"Licence"}
                    />
                }
                variant="outlined"
                error={!isCorrectInput}
                helperText={messageForIncorrectInput}
                fullWidth
                defaultValue={(userInformation) ? (userInformation.licence) : null}
            />
        </Form.Group>)
};