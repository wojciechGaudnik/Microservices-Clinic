import React from "react";

export const DoctorPage = (props) => (
    <div>
        <p>Doctor</p>
        <p>UUID: {props.userDetails.uuid}</p>
        <p>Role: {props.userDetails.role}</p>
    </div>
);

export default DoctorPage