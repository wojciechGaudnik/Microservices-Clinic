import React from "react";

import {Card, CardContent, Grid} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import AppointmentCard from "./AppointmentCardForVisitsComponent";

export const VisitsComponent = ({calendars}) => {
    return(
      <div>
          <Grid container spacing={3} direction="column" justify="center">
            {calendars ? (calendars.map((calendar) =>
                <Grid item xs={12} key={calendar.name}>
                  <Card>
                    <CardContent>
                      <Typography color="textSecondary" gutterBottom>
                        {calendar.name}
                      </Typography>
                      <Grid container direction="row" spacing={3}>
                        <AppointmentCard
                          appointments={calendar.appointments}
                        />
                      </Grid>
                    </CardContent>
                  </Card>
                </Grid>)
            ) : <></>}
          </Grid>
      </div>
    )
};

export default VisitsComponent