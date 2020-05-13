import React, {useReducer} from "react";
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
        }
    }
  };
  const initialState = {
    roleNewUser: "doctor",
    showFailMessage: false,
    showSuccessMessage: false
  };
  const init = (initialState) => initialState;
  const[registerStatus, dispatchRegisterStatus] = useReducer(registerNewUser, initialState, init);

//Fetch
  const fetchRegisterNewUser = async (registerDetails) => {
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
          await fetch(URLs.REGISTER_DOCTOR, nextStepInitDoctor);
          break;
        case "patient":
          const nextStepBodyPatient = {
            patientUUID: result.userUUID,
            firstName: registerDetails.firstName,
            lastName: registerDetails.lastName,
            photoUrl: registerDetails.photoUrl,
            pesel: registerDetails.pesel
          };
          const nextStepInitPatient = {
            method: 'POST',
            body: nextStepBodyPatient,
            headers: {
              'Authorization': result.token,
              'Content-Type': 'application/json;charset=UTF-8',
            }
          };
          await fetch(URLs.REGISTER_PATIENT, nextStepInitPatient);
          break;
      }
      dispatchRegisterStatus({type: "REGISTER_SUCCESSFUL"});
    } catch (e) {
      dispatchRegisterStatus({type: "REGISTER_FAILED"});
    }
  };

  const handleChangeRole = (roleNewUser) => {
    dispatchRegisterStatus({
      type: "CHANGE_REGISTER_ROLE",
      roleNewUser: roleNewUser
    })
  };

  return(
    children({
      fetchRegisterNewUser,
      registerStatus,
      dispatchRegisterStatus,
      handleChangeRole
    })
  )
};

export default ContainerRegisterPage;

