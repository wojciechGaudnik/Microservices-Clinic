import {useEffect, useReducer} from "react";
import {URLs} from "../../../URLs";
import {logOut} from "../../../actions";
import {useHistory} from "react-router";

export const ContainerDoctorPage = ({userInformation, children, userDetails, setStoreUserInformation}) => {
  //History
  const history = useHistory();
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
      case "SETTING_ALL_CALENDARS_INFORMATION_SUCCESS":
        return {
          ...state,
          calendars: action.calendars
        };
      case "SETTING_ALL_CALENDARS_INFORMATION_FAILED":
        return state;
      case "SETTING_ALL_CALENDAR_APPOINTMENTS_INFORMATION_SUCCESS":
        return {
          ...state,
          appointments: [...state.appointments, action.appointments]
        };
      case "SETTING_ALL_CALENDAR_APPOINTMENTS_INFORMATION_FAILED":
        return state;
      case "DELETE_ACCOUNT_SUCCESS":
        return state;
      case "DELETE_ACCOUNT_FAILED":
        return state;
      case "USER_INFORMATION_HAS_BEEN_EDIT_SUCCESS":
        return {
          ...state,
          userInformationHasBeenEdit: !state.userInformationHasBeenEdit
        };
      case "USER_INFORMATION_HAS_BEEN_EDIT_FAILED":
        return state;
      case "SETTING_APPOINTMENTS_TO_CALENDARS_SUCCESS":
        const setAppointments = (calendar) => {
          for (let i = 0; i < state.appointments.length; i++){
            if (calendar.calendarUUID === state.appointments[i].calendarUUID){
              calendar.appointments = state.appointments[i].appointments;
            }
          }
        };
        state.calendars.map((calendar) =>
          setAppointments(calendar)
        );
        return {...state};
      case "SETTING_APPOINTMENT_TO_CALENDARS_FAILED":
        return state;
      case "CHANGE_COMPONENT":
        return {
          ...state,
          componentToShow: action.componentToShow
        };
      default:
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
    calendars: [],
    appointments: [],
    userInformationHasBeenEdit: false,
    componentToShow: 0
  };
  const [doctorPageState, dispatchDoctorPageState] = useReducer(setDoctorPageState, initialState, init);

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
    const fetchForSetAllCalendarsInformation = async () => {
      try {
        const initCalendars = {
          method: 'GET',
          body: null,
          headers: {'Authorization': localStorage.token}
        };
        const responseCalendars = await fetch(URLs.GET_ALL_DOCTOR_CALENDARS(userDetails.uuid), initCalendars)
        const resultCalendars = await responseCalendars.json();
        if (responseCalendars.ok) {
          dispatchDoctorPageState({type: "SETTING_ALL_CALENDARS_INFORMATION_SUCCESS", calendars: resultCalendars})
        } else {
          dispatchDoctorPageState({type: "SETTING_ALL_CALENDARS_INFORMATION_FAILED"})
        }
      } catch (e) {
        dispatchDoctorPageState({type: "SETTING_ALL_CALENDARS_INFORMATION_FAILED"})
      }
    };
    fetchForDoctorInformation();
    fetchForSetAllCalendarsInformation();
  }, [doctorPageState.userInformationHasBeenEdit, setStoreUserInformation, userDetails.uuid]);
  useEffect(() => {
    const fetchForSetAppointmentsInCalendar = async (calendar) => {
      try {
        const initAppointments = {
          method: 'GET',
          body: null,
          headers: {'Authorization':localStorage.token}
        };
        const response = await fetch(URLs.GET_ALL_APPOINTMENTS_IN_GIVEN_CALENDAR(userDetails.uuid, calendar.calendarUUID), initAppointments);
        const result = await response.json();
        const appointments = {
          appointments: result,
          calendarUUID: calendar.calendarUUID
        };
        dispatchDoctorPageState({type: "SETTING_ALL_CALENDAR_APPOINTMENTS_INFORMATION_SUCCESS", appointments: appointments})
      } catch (e) {
        dispatchDoctorPageState({type: "SETTING_ALL_CALENDAR_APPOINTMENTS_INFORMATION_FAILED"})
      }
    };
    try {
      doctorPageState.calendars.map((calendar) =>
        fetchForSetAppointmentsInCalendar(calendar)
      );
    } catch{
      dispatchDoctorPageState({type: "SETTING_ALL_CALENDAR_APPOINTMENTS_INFORMATION_FAILED"})
    }
  }, [doctorPageState.calendars, userDetails.uuid]);
  useEffect(() => {
    if (!(doctorPageState.calendars === [] || doctorPageState.appointments === [])){
      try {
        dispatchDoctorPageState({type: "SETTING_APPOINTMENTS_TO_CALENDARS_SUCCESS"})
      } catch {
        dispatchDoctorPageState({type: "SETTING_APPOINTMENTS_TO_CALENDARS_FAILED"})
      }
    }
  }, [doctorPageState.calendars, doctorPageState.appointments]);
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
      await fetch(URLs.DELETE_DOCTOR(userDetails.uuid), init)
        .catch(() => dispatchDoctorPageState({type: "DELETE_ACCOUNT_FAILED"}));
      dispatchDoctorPageState({type: "DELETE_ACCOUNT_SUCCESS"});
      logOut(history);
    } catch (e) {
      dispatchDoctorPageState({type: "DELETE_ACCOUNT_FAILED"})
    }
  };
  const fetchForChangeUserInformation = async (newUserInformation) => {
    console.log("CLICK!");
    console.log(newUserInformation);
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
      console.log("SUCCESS!");
    } catch (e) {
      dispatchDoctorPageState({type: "USER_INFORMATION_HAS_BEEN_EDIT_FAILED"})
      console.log("FAILED!");
    }
  };
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