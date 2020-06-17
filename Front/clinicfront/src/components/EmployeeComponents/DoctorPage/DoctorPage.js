import React from "react";

import {Container} from "@material-ui/core";
import {LogOutButton} from "../../AdditionalComponents/LogOutBtn/LogOutButton";
import DoctorInfoComponent from "./DoctorPageComponents/DoctorInfoComponent";
import EditDataFormComponent from "./DoctorPageComponents/EditDataFormComponent";
import DeleteAccountComponent from "./DoctorPageComponents/DeleteAccountComponent";
import AppBar from "@material-ui/core/AppBar";
import TabPanel from "../../AdditionalComponents/TabPanel/TabPanel";
import VisitsComponent from "./DoctorPageComponents/VisitsComponent";
import {StylesTab, StylesTabs} from "../../AdditionalComponents/CustomTab/StylesTab";
import TabCustomTypography from "../../AdditionalComponents/CustomTypography/TypesOfCustomTypography/TabCustomTypography";

//CSS Stylesheet
export const styleForMainDiv = {
  marginTop: '30px',
  width: '100%'
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
    <Container style={styleForMainDiv}>
      <Container>
        <LogOutButton/>
      </Container>
      <Container>
        <Container
          style={styleForMainContainer}
        >
          <AppBar position="static">
            <StylesTabs
              value={doctorPageState.componentToShow}
              onChange={onClickChangeTabPanel}
              variant="scrollable"
              scrollButtons="auto"
            >
              <StylesTab
                label={
                  <TabCustomTypography
                    primaryLabel={"Wizyty"}
                    secondaryLabel={"Visits"}
                  />
                }
              />
              <StylesTab
                label={
                  <TabCustomTypography
                    primaryLabel={"Informacje Użytkownika"}
                    secondaryLabel={"User Information"}
                  />
                }
              />
              <StylesTab
                label={
                  <TabCustomTypography
                    primaryLabel={"Edytowanie danych użytkownika"}
                    secondaryLabel={"Edit User Information"}
                  />
                }
              />
              <StylesTab
                label={
                  <TabCustomTypography
                    primaryLabel={"Usuwanie Konta"}
                    secondaryLabel={"Delete Account"}
                  />
                }
              />
            </StylesTabs>
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
      </Container>
    </Container>
  )
};

export default DoctorPage