import {useReducer} from "react";
import {URLs} from "../../../URLs";

export const ContainerRegisterPage = ({children}) => {

  //Here we create reducer
  const registerNewUser = (state, action) => {
    switch (action.type) {
      case "REGISTER_SUCCESSFUL":
        return {
          ...state,
          showSuccessMessage: true,
        };
      case "REGISTER_FAILED":
        return {
          ...state,
          showFailMessage: true
        };
      case "CHANGE_REGISTER_ROLE":
        return {
          ...state,
          roleNewUser: action.roleNewUser
        };
      case "CLOSE_SUCCESS_MESSAGE":
        return {
          ...state,
          showSuccessMessage: false
        };
      case "CLOSE_FAIL_MESSAGE":
        return {
          ...state,
          showFailMessage: false
        };
      case "LOADING_ON":
        return {
          ...state,
          isLoading: true
        }
      case "LOADING_OFF":
        return {
          ...state,
          isLoading: false
        }
      default:
        return state;
    }
  };
  const initialState = {
    roleNewUser: "doctor",
    showFailMessage: false,
    showSuccessMessage: false,
    isLoading: false
  };
  const init = (initialState) => initialState;
  const [registerStatus, dispatchRegisterStatus] = useReducer(registerNewUser, initialState, init);

//Fetch
  const fetchRegisterNewUser = async (registerDetails) => {
    dispatchRegisterStatus({type: "LOADING_ON"});
    try {
      const body = JSON.stringify({
        "email": registerDetails.email,
        "password": registerDetails.password,
        "role": registerDetails.role
      });
      const init = {
        method: 'POST',
        body: body,
        headers: {'Content-Type': 'application/json'}
      };
      const response = await fetch(URLs.REGISTER_USER, init);
      const result = await response.json();

        switch(registerDetails.role) {
          case "doctor":
            const nextStepBodyDoctor = JSON.stringify({
              "doctorUUID": result.userUUID,
              "firstName": registerDetails.firstName,
              "lastName": registerDetails.lastName,
              "licence": registerDetails.licence,
              "photoUrl": registerDetails.photoUrl
            });
            const nextStepInitDoctor = {
              method: 'POST',
              body: nextStepBodyDoctor,
              headers: {
                'Authorization': result.token,
                'Content-Type': 'application/json;charset=UTF-8',
              }
            };
            const responseDoctor = await fetch(URLs.REGISTER_DOCTOR, nextStepInitDoctor);
            if (responseDoctor.ok){
              dispatchRegisterStatus({type: "REGISTER_SUCCESSFUL"})
            } else {
              dispatchRegisterStatus({type: "REGISTER_FAILED"})
            }
            break;
          case "patient":
            const nextStepBodyPatient = JSON.stringify({
              patientUUID: result.userUUID,
              firstName: registerDetails.firstName,
              lastName: registerDetails.lastName,
              photoUrl: registerDetails.photoUrl,
              pesel: registerDetails.pesel
            });
            const nextStepInitPatient = {
              method: 'POST',
              body: nextStepBodyPatient,
              headers: {
                'Authorization': result.token,
                'Content-Type': 'application/json;charset=UTF-8',
              }
            };
            const responsePatient = await fetch(URLs.REGISTER_PATIENT, nextStepInitPatient);
            if (responsePatient.ok) {
              dispatchRegisterStatus({type: "REGISTER_SUCCESSFUL"})
            } else {
              dispatchRegisterStatus({type: "REGISTER_FAILED"})
            }
            break;
          default:
            dispatchRegisterStatus({type: "REGISTER_FAILED"});
            break;
        }
    } catch (e) {
      dispatchRegisterStatus({type: "REGISTER_FAILED"});
    }
    dispatchRegisterStatus({type: "LOADING_OFF"});
  };
  const handleChangeRole = (roleNewUser) => {
    dispatchRegisterStatus({
      type: "CHANGE_REGISTER_ROLE",
      roleNewUser: roleNewUser
    })
  };
  const closeAlertSuccessMessage = () => {
    dispatchRegisterStatus({type: "CLOSE_SUCCESS_MESSAGE"})
  }
  const closeAlertFailedMessage = () => {
    dispatchRegisterStatus({type: "CLOSE_FAIL_MESSAGE"})
  }

  return(
    children({
      fetchRegisterNewUser,
      registerStatus,
      closeAlertSuccessMessage,
      closeAlertFailedMessage,
      handleChangeRole
    })
  )
};

export default ContainerRegisterPage;

