import React from "react";
import {Container} from "react-bootstrap";
import {LogOutButton} from "../../AdditionalComponents/LogOutBtn/LogOutButton";
import AppBar from "@material-ui/core/AppBar";
import {Tab, Tabs} from "@material-ui/core";
import TabPanel from "../../AdditionalComponents/TabPanel";
import DeleteAccountComponent from "../DoctorPage/DoctorPageComponents/DeleteAccountComponent";
import EditDataFormComponent from "./PatientPageComponents/EditDataFormComponent";
import PatientInfoComponent from "./PatientPageComponents/PatientInfoComponent";
import error_401 from "../../../images/error_401.jpg";


//CSS Stylesheet
export const styleForMainDiv = {
  margin: '30px'
};

export const styleForSubContainer = {
  color: 'black',
  padding: '10px 30px 10px 30px',
  marginBottom: '15px',
  width: '50%',
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

export const styleForBackToLoginPageButton = {
  marginTop: '10px',
  border: '2px solid black'
};

export const styleForMainContainer = {
  marginTop: "50px"
};

export const PatientPage = (props) => {
  const {
    patientPageState,
    userInformation,
    onClickChangeTabPanel,
    fetchForChangeUserInformation,
    fetchForDeleteAccount,
  } = props;

  return(
    <div style={styleForMainDiv}>
      <div>
        <LogOutButton/>
      </div>
      <div>
        <Container style={styleForMainContainer}>
          <AppBar position="static">
            <Tabs value={patientPageState.componentToShow} onChange={onClickChangeTabPanel} variant="fullWidth">
              <Tab label="Visits"/>
              <Tab label="Visit Registration"/>
              <Tab label="User Information"/>
              <Tab label="Edit User Information"/>
              <Tab label="Delete Account"/>
            </Tabs>
          </AppBar>
          <TabPanel value={patientPageState.componentToShow} index={0}>
            {/*<VisitsComponent/>*/}
            hello
          </TabPanel>
          <TabPanel value={patientPageState.componentToShow} index={1}>
            hello
            {/*<VisitRegistration/>*/}
          </TabPanel>
          <TabPanel value={patientPageState.componentToShow} index={2}>
            <PatientInfoComponent
              patientInformation={patientPageState.patientInformation}
            />
          </TabPanel>
          <TabPanel value={patientPageState.componentToShow} index={3}>
            <EditDataFormComponent
              fetchRequest={fetchForChangeUserInformation}
              userInformation={userInformation}
            />
          </TabPanel>
          <TabPanel value={patientPageState.componentToShow} index={4}>
            <DeleteAccountComponent
              fetchRequest={fetchForDeleteAccount}
            />
          </TabPanel>
        </Container>
      </div>
    </div>)
};

export default PatientPage