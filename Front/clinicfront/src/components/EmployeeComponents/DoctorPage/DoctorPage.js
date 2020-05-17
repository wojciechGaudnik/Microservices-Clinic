import React from "react";

import {Container, Tab, Tabs} from "@material-ui/core";
import {LogOutButton} from "../../AdditionalComponents/LogOutBtn/LogOutButton";
import DoctorInfoComponent from "./DoctorPageComponents/DoctorInfoComponent";
import EditDataFormComponent from "./DoctorPageComponents/EditDataFormComponent";
import DeleteAccountComponent from "./DoctorPageComponents/DeleteAccountComponent";
import AppBar from "@material-ui/core/AppBar";
import TabPanel from "../../AdditionalComponents/TabPanel";
import error_401 from "../../../images/error_401.jpg";
import VisitsComponent from "./DoctorPageComponents/VisitsComponent";

//CSS Stylesheet
export const styleForMainDiv = {
  margin: '30px',
  width: '100%'
};

export const styleForMainDivError = {
  margin: '0px',
  textAlign: 'center',
  width: '100vw',
  height: '100vh',
  backgroundImage: `url(${error_401})`,
  backgroundPosition: 'center',
  backgroundRepeat: 'no-repeat',
  backgroundSize: 'cover',
};

export const styleForMainGrid = {
  marginBottom: '15px',
  marginRight: '25%',
  marginLeft: '25%',
  width: '50%'
};

export const styleForBackToLoginPageButton = {
  marginTop: '10px',
  border: '2px solid black'
};

export const styleForMainContainer = {
  marginTop: "50px"
};

export const DoctorPage = (props) => {
  const {
    doctorPageState,
    fetchForDeleteAccount,
    fetchForChangeUserInformation,
    onClickChangeTabPanel,
    userInformation
  } = props;

  //Main HTML return
  return (
    <div style={styleForMainDiv}>
      <div>
        <LogOutButton/>
      </div>
      <div>
        <Container
          style={styleForMainContainer}
        >
          <AppBar position="static">
            <Tabs
              value={doctorPageState.componentToShow}
              onChange={onClickChangeTabPanel} variant="fullWidth"
            >
              <Tab label="Visits"/>
              <Tab label="User Information"/>
              <Tab label="Edit User Information"/>
              <Tab label="Delete Account"/>
            </Tabs>
          </AppBar>
          <TabPanel value={doctorPageState.componentToShow} index={0}>
            <VisitsComponent
              calendars={doctorPageState.calendars}
            />
          </TabPanel>
          <TabPanel value={doctorPageState.componentToShow} index={1}>
            <DoctorInfoComponent
              doctorInformation={doctorPageState.doctorInformation}
              userInformationHasBeenEdit={doctorPageState.userInformationHasBeenEdit}
            />
          </TabPanel>
          <TabPanel value={doctorPageState.componentToShow} index={2}>
            <EditDataFormComponent
              fetchRequest={fetchForChangeUserInformation}
              userInformation={userInformation}
            />
          </TabPanel>
          <TabPanel value={doctorPageState.componentToShow} index={3}>
            <DeleteAccountComponent
              fetchRequest={fetchForDeleteAccount}
            />
          </TabPanel>
        </Container>
      </div>
    </div>
  )
};

export default DoctorPage