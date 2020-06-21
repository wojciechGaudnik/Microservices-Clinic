import {Col, Form} from "react-bootstrap";
import React, {useState} from "react";
import StylesTextField from "../../CustomTextField/StylesTextField";
import TextFieldCustomTypography from "../../CustomTypography/TypesOfCustomTypography/TextFieldLabelCustomTypography";

export const PhotoURLForm = (props) => {
  const { handleChange, validation, userInformation, setIsCorrectInputInForms} = props;

  const [isCorrectInput, setIsCorrectInput] = useState(true);
  const [messageForIncorrectInput, setMessageForIncorrectInput] = useState(null);
  const regexp = /^(?:(?:https?|ftp):\/\/)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/\S*)?$/;

  const setGoodInputInAllStates = () => {
    setIsCorrectInput(true);
    setIsCorrectInputInForms({photoUrlForm: true});
  };

  const setWrongInputInAllStates = () => {
    setIsCorrectInput(false);
    setIsCorrectInputInForms({photoUrlForm: false});
  };


  //Validation for input data
  const checkInputCorrect = (e) => {
    console.log(e.target.value)
    if (e.target.value.length === 0){
      setWrongInputInAllStates();
      setMessageForIncorrectInput("The field cannot be empty")
    } else if (!regexp.test(e.target.value)){
      setWrongInputInAllStates();
      setMessageForIncorrectInput("The text is not url")
    } else {
      setGoodInputInAllStates();
      setMessageForIncorrectInput(null);
    }
  };
  return (
    <Form.Group as={Col}>
      <StylesTextField
        onChange={(e) => {
          handleChange({photoUrl : e.target.value});
          if (validation){checkInputCorrect(e)}
        }}
        name="photoUrl"
        label={
          <TextFieldCustomTypography
            primaryLabel={"Link do zdjęcia"}
            secondaryLabel={"Photo URL"}
          />
        }
        variant="outlined"
        error={!isCorrectInput}
        helperText={messageForIncorrectInput}
        fullWidth
        defaultValue={(userInformation) ? (userInformation.photoUrl) : null}
      />
    </Form.Group>)
};