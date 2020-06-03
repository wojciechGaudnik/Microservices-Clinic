import React, {useEffect, useState} from "react";
import {useFormFields} from "../../../actions";

const ContainerForFormForInputUserInformation = (props) => {
  const {
    children,
    role,
    fetchRequest,
    showEmailForm,
    showPasswordForm,
    showFirstNameForm,
    showLastNameForm,
    showLicenceForm,
    showPhotoURLForm,
    showPeselForm,
    submitButtonTitle,
    submitButtonAdditionalActions = () => {}
  } = props;

  const [submitButtonAvailable, setSubmitButtonAvailable] = useState(false);
  const [validation, setValidation] = useState(true);
  const [registerAs, setRegisterAs] = useState(role);
  const [userInformation, setUserInformation] = useFormFields({
    firstName:  null,
    lastName:   null,
    licence:    null,
    photoUrl:   null,
    email:      null,
    password:   null,
    pesel:      null,
  });
  const [isCorrectInputInEachForms, setIsCorrectInputFromEachForms] = useState({
    emailForm:      !showEmailForm,
    firstNameForm:  !showFirstNameForm,
    lastNameForm:   !showLastNameForm,
    licenceForm:    !showLicenceForm,
    passwordForm:   !showPasswordForm,
    peselForm:      !showPeselForm ,
    photoURLForm:   !showPhotoURLForm,
  });

  useEffect(() => {
    setRegisterAs(role);
    setIsCorrectInputFromEachForms({
      emailForm:      !showEmailForm,
      firstNameForm:  !showFirstNameForm,
      lastNameForm:   !showLastNameForm,
      licenceForm:    !showLicenceForm,
      passwordForm:   !showPasswordForm,
      peselForm:      !showPeselForm ,
      photoURLForm:   !showPhotoURLForm,
    });
  }, [role,
    setRegisterAs,
    showEmailForm,
    showFirstNameForm,
    showLastNameForm,
    showLicenceForm,
    showPasswordForm,
    showPeselForm ,
    showPhotoURLForm,]);
  useEffect(() => {
    setValidation(submitButtonTitle !== "Log In");
    if (submitButtonTitle === "Edit"){
      setIsCorrectInputFromEachForms({
        emailForm:      true,
        firstNameForm:  true,
        lastNameForm:   true,
        licenceForm:    true,
        passwordForm:   true,
        peselForm:      true,
        photoURLForm:   true,
      })
    }
  }, [submitButtonTitle]);
  useEffect(() => {
    const checkCorrectInputInAllFormsForButtonAvailable = () => {
      for (const isCorrectInput in isCorrectInputInEachForms){
        if (isCorrectInputInEachForms[isCorrectInput] === false){
          return false;
        }
      }
      return true;
    };

    setSubmitButtonAvailable(() => checkCorrectInputInAllFormsForButtonAvailable())
  }, [isCorrectInputInEachForms]);

  const setIsCorrectInputInForms = (isInputCorrectObject) => {
    setIsCorrectInputFromEachForms({...isCorrectInputInEachForms, ...isInputCorrectObject})};
  const handleChange = (event) => {
    setUserInformation(event);
  };
  const onSubmit = (e) => {
    e.preventDefault();
    fetchRequest({...userInformation, role: registerAs});
    submitButtonAdditionalActions();
  };

  return(children({
    onSubmit,
    showEmailForm,
    showPasswordForm,
    showFirstNameForm,
    showLastNameForm,
    showLicenceForm,
    showPhotoURLForm,
    showPeselForm,
    submitButtonTitle,
    submitButtonAvailable,
    validation,
    handleChange,
    setIsCorrectInputInForms,
    props
  }))
};

export default ContainerForFormForInputUserInformation