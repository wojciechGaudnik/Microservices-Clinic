import React from "react";
import Typography from "@material-ui/core/Typography";
import {Card, CardContent} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";

export const AppointmentCard = ({appointments}) => {
  return (
    <>
    {appointments ? (appointments.map((appointment) =>
      <Grid item xs={3} key={appointment.appointmentUUID}>
        <Card>
          <CardContent>
            <Typography color="textSecondary" gutterBottom>
              UUID: {appointment.appointmentUUID}
            </Typography>
            <Typography variant="h5" component="h2">
              Patient First Name: {appointment.patientFirstName}
            </Typography>
            <Typography color="textSecondary">
              Time:
            </Typography>
            <Typography variant="body2" component="p">
              Info
            </Typography>
          </CardContent>
        </Card>
      </Grid>)) : <></>}
    </>)
};

export default AppointmentCard