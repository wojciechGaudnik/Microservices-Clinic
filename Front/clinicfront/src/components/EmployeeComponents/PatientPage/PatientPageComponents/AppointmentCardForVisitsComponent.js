import React, {useEffect, useState} from "react";
import Typography from "@material-ui/core/Typography";
import {Card, CardContent} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";

export const AppointmentCard = (props) => {
    const {
        sendFetchRequestForGetAllAppointmentsForGivenCalendar,
        doctorUUID,
        calendarUUID
    } = props;

    const [allAppointmentsInCalendar, setAllAppointmentsInCalendar] = useState(null);
    const [key, setKey] = useState(0);

    useEffect(() => {
        sendFetchRequestForGetAllAppointmentsForGivenCalendar(doctorUUID ,calendarUUID, setAllAppointmentsInCalendar);
    }, []);

    return allAppointmentsInCalendar ?
        allAppointmentsInCalendar.map((appointment, index) =>
            <Grid item xs={3} key={index}>
                <Card>
                    <CardContent>
                        <Typography color="textSecondary" gutterBottom>
                            UUID:
                        </Typography>
                        <Typography variant="h5" component="h2">
                            {appointment.appointmentUUID}
                        </Typography>
                        <Typography color="textSecondary">
                            Time:
                        </Typography>
                        <Typography variant="body2" component="p">
                            Info
                        </Typography>
                    </CardContent>
                </Card>
            </Grid>
        ) : null
};

export default AppointmentCard