import {useEffect, useReducer} from "react";

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
    primaryLabel,
    secondaryLabel,
    userInformation,
    submitButtonAdditionalActions = () => {}
  } = props;

  const setFormState = (state, action) => {
    switch (action.type) {
      case "SET_USER_INFORMATION":
        return {
          ...state,
          userInformation: {
            ...state.userInformation,
            ...action.userInformation
          }
        };
      case "SET_CORRECT_INPUT":
        return {
          ...state,
          correctInput: {
            ...state.correctInput,
            ...action.correctInput
          }
        };
      case "SET_TRUE_CORRECT_INPUT":
        return {
          ...state,
          correctInput: {
            emailForm:      true,
            firstNameForm:  true,
            lastNameForm:   true,
            licenceForm:    true,
            passwordForm:   true,
            peselForm:      true,
            photoURLForm:   true
          }
        }
      case "SET_INPUTS":
        return {
          ...state,
          correctInput: action.inputCorrectness
        }
      case "SET_ON_VALIDATION":
        return {
          ...state,
          validation: true
        }
      case "SET_OFF_VALIDATION":
        return {
          ...state,
          validation: false
        }
      case "SET_ON_BUTTON":
        return {
          ...state,
          submitButtonAvailable: true
        }
      case "SET_OFF_BUTTON":
        return {
          ...state,
          submitButtonAvailable: false
        }
      default:
        return state;
    }
  };
  const init = (initialState) => initialState;
  const initialState = {
    userInformation: {
      firstName:  null,
      lastName:   null,
      licence:    null,
      photoUrl:   null,
      email:      null,
      password:   null,
      pesel:      null,
    },
    correctInput: {
      firstNameForm:  !showFirstNameForm,
      lastNameForm:   !showLastNameForm,
      licenceForm:    !showLicenceForm,
      photoUrlForm:   !showPhotoURLForm,
      emailForm:      !showEmailForm,
      passwordForm:   !showPasswordForm,
      peselForm:      !showPeselForm,
    },
    validation: true,
    submitButtonAvailable: false
  };
  const [formComponentState, dispatchFormComponentState] = useReducer(setFormState, initialState, init)

  useEffect(() => {
    if (secondaryLabel === "Log In"){
      dispatchFormComponentState({type: "SET_OFF_VALIDATION"})
    } else if (secondaryLabel === "Edit"){
      dispatchFormComponentState({type: "SET_TRUE_CORRECT_INPUT"})
    }
  }, [secondaryLabel]);
  useEffect(() => {
    if (userInformation !== null){
      dispatchFormComponentState({type: "SET_USER_INFORMATION", userInformation: userInformation})
    }
  }, [userInformation])
  useEffect(() => {
    const checkCorrectInputInAllFormsForButtonAvailable = () => {
      for (const inputCompatibility in formComponentState.correctInput){
        if (formComponentState.correctInput.hasOwnProperty(inputCompatibility)){
          if (formComponentState.correctInput[inputCompatibility] === false){
            return false;
          }
        }
      }
      return true;
    };
    if (checkCorrectInputInAllFormsForButtonAvailable()){
      dispatchFormComponentState({type: "SET_ON_BUTTON"})
    }else {
      dispatchFormComponentState({type: "SET_OFF_BUTTON"})
    }
  }, [formComponentState.correctInput]);
  const setIsCorrectInputInForms = (isInputCorrectObject) => {
    dispatchFormComponentState({type: "SET_CORRECT_INPUT", correctInput:isInputCorrectObject})
  };
  const handleChange = (userInformation) => {
    dispatchFormComponentState({type: "SET_USER_INFORMATION", userInformation: userInformation});
  };
  const onSubmit = (e) => {
    e.preventDefault();
    fetchRequest({...formComponentState.userInformation, role: role});
    submitButtonAdditionalActions();
  };
  useEffect(() => {
    dispatchFormComponentState({
      type: "SET_INPUTS",
      inputCorrectness: {
        firstNameForm:  !showFirstNameForm,
        lastNameForm:   !showLastNameForm,
        licenceForm:    !showLicenceForm,
        photoUrlForm:   !showPhotoURLForm,
        emailForm:      !showEmailForm,
        passwordForm:   !showPasswordForm,
        peselForm:      !showPeselForm,
      }
    })
  }, [role, showFirstNameForm,showLastNameForm, showLicenceForm, showPhotoURLForm, showEmailForm, showPasswordForm, showPeselForm,])

  return(children({
    onSubmit,
    showEmailForm,
    showPasswordForm,
    showFirstNameForm,
    showLastNameForm,
    showLicenceForm,
    showPhotoURLForm,
    showPeselForm,
    primaryLabel,
    secondaryLabel,
    submitButtonAvailable: formComponentState.submitButtonAvailable,
    validation: formComponentState.validation,
    handleChange,
    setIsCorrectInputInForms,
    userInformation
  }))
};

export default ContainerForFormForInputUserInformation