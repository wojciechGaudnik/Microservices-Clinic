import {ContainerForUserInformation} from "../../../AdditionalComponents/ContainerForUserInformation/ContainerForUserInformation";
import React from "react";

export const DoctorInfoComponent = (props) => {
    return(
        <ContainerForUserInformation
            {...props}
            titleRole={"DOCTOR"}
            firstName={true}
            lastName={true}
            licence={true}
            calendars={true}
            specializations={true}
            medicalUnits={true}
        />
    )
};

export default DoctorInfoComponent