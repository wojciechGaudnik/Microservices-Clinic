import React, {useEffect, useState} from "react";
import {
    sendFetchRequestForGetAllAppointmentsForGivenCalendar,
    sendFetchRequestForGetAllCalendars
} from "./ContainersForDoctorPageComponents/ContainerForVisitComponent";

import {Card, CardContent, Grid} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import AppointmentCard from "./AppointmentCardForVisitsComponent";

export const VisitsComponent = (props) => {
    const {doctorUUID} = props;

    const [allCalendars, setAllCalendars] = useState(null);

    useEffect(() => {
        sendFetchRequestForGetAllCalendars(doctorUUID, setAllCalendars);
    }, []);

    const displayCalendars = allCalendars ?
        allCalendars.map((calendar) =>
            <Grid item xs={12} key={calendar.name}>
                <Card>
                    <CardContent>
                        <Typography color="textSecondary" gutterBottom>
                            {calendar.name}
                        </Typography>
                        <Grid container direction="row" spacing={3}>
                            <AppointmentCard
                                sendFetchRequestForGetAllAppointmentsForGivenCalendar={sendFetchRequestForGetAllAppointmentsForGivenCalendar}
                                doctorUUID={doctorUUID}
                                calendarUUID={calendar.calendarUUID}
                            />
                        </Grid>
                    </CardContent>
                </Card>
            </Grid>
        ) : null;

    return(
      <div>
          <Grid container spacing={3} direction="column" justify="center">
              {displayCalendars}
          </Grid>
      </div>
    )
};

export default VisitsComponent