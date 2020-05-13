import {useEffect, useReducer} from "react";
import {URLs} from "../../../URLs";

export const ContainerDoctorPage = ({userInformation, children, userDetails, setStoreUserInformation}) => {
  //Reducer
  const setDoctorPageState = (state, action) => {
    switch (action.type) {
      case "SETTING_INFORMATION_SUCCESS":
        return {
          ...state,
          doctorInformation: action.doctorInformation
        };
      case "SETTING_INFORMATION_FAILED":
        return state;
      case "DELETE_ACCOUNT_SUCCESS":
        return state;
      case "DELETE_ACCOUNT_FAILED":
        return state;
      case "CHANGE_COMPONENT":
        return {
          ...state,
          componentToShow: action.componentToShow
        };
      case "USER_INFORMATION_HAS_BEEN_EDIT_SUCCESS":
        return {
          ...state,
          userInformationHasBeenEdit: true
        };
      case "USER_INFORMATION_HAS_BEEN_EDIT_FAILED":
        return state;
    }
  };
  const init = (initialState) => initialState;
  const initialState = {
    doctorInformation: {
      doctorUUID: null,
      firstName: null,
      lastName: null,
      photoUrl: null,
      licence: null,
      calendarsUUID: null,
      specializations: null,
      patientsUUIDs: null,
      medicalUnitsUUID: null
    },
    userInformationHasBeenEdit: false,
    componentToShow: 0
  };
  const [doctorPageState, dispatchDoctorPageState] = useReducer(setDoctorPageState, initialState, init);

  //Fetch
  const fetchForDeleteAccount = async () => {
    try {
      const init = {
        method: 'DELETE',
        body: null,
        headers: {
          'Authorization': localStorage.token,
          'Content-Type': 'application/json',
        }
      };
      await fetch(URLs.DELETE_DOCTOR(userDetails.uuid), init);
      dispatchDoctorPageState({type: "DELETE_ACCOUNT_SUCCESS"})
    } catch (e) {
      dispatchDoctorPageState({type: "DELETE_ACCOUNT_FAILED"})
    }
  };

  const fetchForChangeUserInformation = async (newUserInformation) => {
    try {
      const body = JSON.stringify({
        "email": newUserInformation.email,
        "password": newUserInformation.password,
        "firstName": newUserInformation.firstName,
        "lastName": newUserInformation.lastName,
        "photoUrl": newUserInformation.photoUrl,
        "licence": newUserInformation.licence,
      });
      const init = {
        method: 'PATCH',
        body: body,
        headers: {
          'Authorization': localStorage.token,
          'Content-Type': 'application/json',
        }
      };
      await fetch(URLs.CHANGE_DOCTOR_INFORMATION(userDetails.uuid), init);
      dispatchDoctorPageState({type: "USER_INFORMATION_HAS_BEEN_EDIT_SUCCESS"});
    } catch (e) {
      dispatchDoctorPageState({type: "USER_INFORMATION_HAS_BEEN_EDIT_FAILED"})
    }
  };

  useEffect(() => {
    const fetchForDoctorInformation = async () => {
      try {
        const init = {
          method: 'GET',
          body: null,
          headers: {'Authorization': localStorage.token}
        };
        const response = await fetch(URLs.GET_DOCTOR_INFORMATION(userDetails.uuid), init);
        const result = await response.json();
        setStoreUserInformation(result);
        dispatchDoctorPageState({type: "SETTING_INFORMATION_SUCCESS", doctorInformation: result})
      } catch (e) {
        dispatchDoctorPageState({type: "SETTING_INFORMATION_FAILED"})
      }
    };
    fetchForDoctorInformation();
  }, [doctorPageState.userInformationHasBeenEdit]);

  const onClickChangeTabPanel = (event, newValue) => {
    dispatchDoctorPageState({type: "CHANGE_COMPONENT", componentToShow: newValue});
  };

  return(children({
    doctorPageState,
    fetchForDeleteAccount,
    fetchForChangeUserInformation,
    onClickChangeTabPanel,
    userDetails,
    userInformation,
  }))
};

export default ContainerDoctorPage