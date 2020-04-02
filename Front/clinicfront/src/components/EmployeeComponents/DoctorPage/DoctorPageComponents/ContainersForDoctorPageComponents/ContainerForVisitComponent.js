import {URLs} from "../../../../../URLs";
import {sendRequestByGivenDetails} from "../../../../../actions/fetchRequest";

export const styleForCalendarCard = {
    width: '100%',
    minWidth: '50px',
};

export const sendFetchRequestForGetAllCalendars = (doctorUUID, setAllCalendars) => {
    const url = URLs.GET_ALL_DOCTOR_CALENDARS(doctorUUID);

    const method = 'GET';

    const body = null;

    const headers = {
        'Authorization':localStorage.token
    };

    const setInStateFunction = (responseData) => {setAllCalendars(responseData)};

    const specialFunction = null;

    sendRequestByGivenDetails(
        url,
        method,
        body,
        headers,
        setInStateFunction,
        specialFunction
    )
};

export const sendFetchRequestForGetAllAppointmentsForGivenCalendar = (doctorUUID, calendarUUID, setAllAppointmentsInCalendar) => {
    const url = URLs.GET_ALL_APPOINTMENTS_IN_GIVEN_CALENDAR(doctorUUID, calendarUUID);

    const method = 'GET';

    const body = null;

    const headers = {
        'Authorization':localStorage.token
    };

    const setInStateFunction = (responseData) => {setAllAppointmentsInCalendar(responseData)};

    const specialFunction = null;

    sendRequestByGivenDetails(
        url,
        method,
        body,
        headers,
        setInStateFunction,
        specialFunction
    )
};