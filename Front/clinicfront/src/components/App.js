import React from 'react';
import {BrowserRouter} from 'react-router-dom';
import {Route, Switch} from "react-router";
import 'bootstrap/dist/css/bootstrap.min.css';
import {AssistantPage} from "./EmployeeComponents/AssistantPage/AssistantPage";
import MainPage from "./MainComponents/MainPage/MainPage";
import ContainerDoctorPage from "./EmployeeComponents/DoctorPage/ReduxContainerDoctorPage";
import DoctorPage from "./EmployeeComponents/DoctorPage/DoctorPage";
import PatientPage from "./EmployeeComponents/PatientPage/PatientPage";
import ContainerPatientPage from "./EmployeeComponents/PatientPage/ReduxContainerPatientPage";
import ThemeProvider from "@material-ui/styles/ThemeProvider";
import AppTheme from "./AppTheme";

const App = () => (
    <ThemeProvider theme={AppTheme}>
        <BrowserRouter>
            <div className="App">
                <Switch>
                    <Route exact path="/">
                      <MainPage/>
                    </Route>
                    <Route path="/doctor">
                      <ContainerDoctorPage>
                        {({
                            doctorPageState,
                            fetchForDeleteAccount,
                            fetchForChangeUserInformation,
                            onClickChangeTabPanel,
                            userInformation
                        }) => (
                          <DoctorPage
                            doctorPageState={doctorPageState}
                            fetchForDeleteAccount={fetchForDeleteAccount}
                            fetchForChangeUserInformation={fetchForChangeUserInformation}
                            onClickChangeTabPanel={onClickChangeTabPanel}
                            userInformation={userInformation}
                          />
                        )}
                      </ContainerDoctorPage>
                    </Route>
                    <Route path="/patient">
                      <ContainerPatientPage>
                        {({
                            patientPageState,
                            userInformation,
                            onClickChangeTabPanel,
                            fetchForChangeUserInformation,
                            fetchForDeleteAccount
                          }) => (
                          <PatientPage
                            patientPageState={patientPageState}
                            userInformation={userInformation}
                            onClickChangeTabPanel={onClickChangeTabPanel}
                            fetchForChangeUserInformation={fetchForChangeUserInformation}
                            fetchForDeleteAccount={fetchForDeleteAccount}
                          />
                        )}
                      </ContainerPatientPage>
                    </Route>
                    <Route path="/assistant">
                      <AssistantPage/>
                    </Route>
                </Switch>
            </div>
        </BrowserRouter>
    </ThemeProvider>
);

export default App;
