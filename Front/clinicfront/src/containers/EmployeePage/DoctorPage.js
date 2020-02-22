import React, {useEffect, useState} from "react";
import {getInfo} from "../../actions";

export const DoctorPage = (props) => {
    const [userInformation, setUserInformation] = useState({
        doctorUUID: "empty",
        firstName: "empty",
        lastName: "empty",
        photoURL: "empty",
        licence: "empty",
        calendars: "empty",
        specializations: "empty",
        medicalUnits: "empty"
    });

    useEffect(() => {
        getInfo(props.userDetails.uuid, {setUserInformation});
        props.setStoreUserInformation(userInformation);
    }, [userInformation.doctorUUID]);

    return(
        <div>
            <p>Doctor</p>
            <p>UUID: {props.userDetails.uuid}</p>
            <p>Role: {props.userDetails.role}</p>
            <p>FirstName: {userInformation.firstName}</p>
            <p>LastName: {userInformation.lastName}</p>
            <p>Licence: {userInformation.licence}</p>
            <p>Calendars: {userInformation.calendars}</p>
            <p>Specialization: {userInformation.specializations}</p>
            <p>Medical Units: {userInformation.medicalUnits}</p>
        </div>
    )
};

export default DoctorPage