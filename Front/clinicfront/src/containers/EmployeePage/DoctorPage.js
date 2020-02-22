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
            <p>FirstName: ".{userInformation.firstName}."</p>
            <p>LastName: ".{userInformation.firstName}."</p>
            <p>Licence: ".{userInformation.firstName}."</p>
            <p>Calendars: ".{userInformation.firstName}."</p>
            <p>Specialization: ".{userInformation.firstName}."</p>
            <p>Medical Units: ".{userInformation.firstName}."</p>
        </div>
    )
};

export default DoctorPage