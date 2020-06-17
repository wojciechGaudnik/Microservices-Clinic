import React, {useEffect, useState} from "react";
import {
    sendFetchRequestForGetAllAppointmentsForGivenCalendar,
    sendFetchRequestForGetAllCalendars
} from "./ContainersForDoctorPageComponents/ContainerForVisitComponent";

import {Card, CardContent, Grid} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import AppointmentCard from "./AppointmentCardForVisitsComponent";

export const VisitsComponent = (props) => {
    return(
      <div>
          <p>Hello</p>
      </div>
    )
};

export default VisitsComponent