import {ContainerForUserInformation} from "../../../AdditionalComponents/ContainerForUserInformation/ContainerForUserInformation";
import React from "react";

export const PatientInfoComponent = (props) => {
  return(
    <ContainerForUserInformation
      {...props}
      userInformation={props.patientInformation}
      primaryTitleRole={"Pacjent"}
      secondaryTitleRole={"Patient"}
      firstName={true}
      lastName={true}
      pesel={true}
    />
  )
};

export default PatientInfoComponent