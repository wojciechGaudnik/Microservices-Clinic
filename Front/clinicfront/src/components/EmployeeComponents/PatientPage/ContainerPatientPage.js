import {useHistory} from "react-router";
import {useEffect, useReducer} from "react";
import {URLs} from "../../../URLs";
import {logOut} from "../../../actions";

export const ContainerPatientPage = ({userDetails, userInformation, setStoreUserInformation, children}) => {

  //History
  const history = useHistory();
  //Reducer
  const setPatientPageState = (state, action) => {
    switch (action.type) {
      case "SETTING_INFORMATION_SUCCESS":
        return {
          ...state,
          patientInformation: action.patientInformation
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
      default:
        return state;
    }
  };
  const init = (initialState) => initialState;
  const initialState = {
    patientInformation: {
      patientUUID: null,
      pesel: null,
      firstName: null,
      lastName: null,
      photoUrl: null,
      visits: []
    },
    userInformationHasBeenEdit: false,
    componentToShow: 0,
  };
  const[patientPageState, dispatchPatientPageState] = useReducer(setPatientPageState, initialState, init);

  //Fetch
  const fetchForDeleteAccount = async () => {
    try{
      const init = {
        method: 'DELETE',
        body: null,
        headers: {
          'Authorization': localStorage.token,
          'Content-Type': 'application/json',
        }
      };
      await fetch(URLs.DELETE_PATIENT(userDetails.uuid), init)
        .catch(() => dispatchPatientPageState({type: "DELETE_ACCOUNT_FAILED"}));
      dispatchPatientPageState({type: "DELETE_ACCOUNT_SUCCESS"});
      logOut(history);
    } catch (e) {
      dispatchPatientPageState({type: "DELETE_ACCOUNT_FAILED"})
    }
  };

  const fetchForChangeUserInformation = async (newUserInformation) => {
    try{
      const body = JSON.stringify({
        "firstName" :newUserInformation.firstName,
        "lastName" :newUserInformation.lastName,
        "pesel" :newUserInformation.pesel,
        "photoUrl" :newUserInformation.photoUrl
      });
      const init = {
        method: 'PATCH',
        body: body,
        headers: {
          'Authorization': localStorage.token,
          'Content-Type': 'application/json',
        }
      };
      await fetch(URLs.CHANGE_PATIENT_INFORMATION(userDetails.uuid), init)
        .catch(() => dispatchPatientPageState({type: "USER_INFORMATION_HAS_BEEN_EDIT_FAILED"}));
      dispatchPatientPageState({type: "USER_INFORMATION_HAS_BEEN_EDIT_SUCCESS"})
    } catch (e) {
      dispatchPatientPageState({type: "USER_INFORMATION_HAS_BEEN_EDIT_FAILED"})
    }
  };

  useEffect(() => {
    const fetchForPatientInformation = async () => {
      try {
        const init = {
          method: 'GET',
          body: null,
          headers: {'Authorization': localStorage.token}
        };
        const response = await fetch(URLs.GET_PATIENT_INFORMATION(userDetails.uuid), init);
        const result = await response.json();
        setStoreUserInformation(result);
        dispatchPatientPageState({type: "SETTING_INFORMATION_SUCCESS", patientInformation:result})
      }catch (e) {
        dispatchPatientPageState({type: "SETTING_INFORMATION_FAILED"})
      }
    };
    fetchForPatientInformation();
    console.log("Hello")
  }, [patientPageState.userInformationHasBeenEdit, setStoreUserInformation, userDetails.uuid]);

  const onClickChangeTabPanel = (event, newValue) => {
    dispatchPatientPageState({type: "CHANGE_COMPONENT", componentToShow: newValue})
  };

  return(children({
    patientPageState,
    userInformation,
    onClickChangeTabPanel,
    fetchForChangeUserInformation,
    fetchForDeleteAccount,
  }))
};