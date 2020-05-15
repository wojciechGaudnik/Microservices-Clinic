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

/*
    TODO:
        X 1. Zrefaktorować zapytania fetchowe użyć useReducers
        X 2. Zrefaktorować useEffects
        X 3. Zrefaktorować useState na obiekty a nie osobne stany
        X 4. Zbudowanie prawidłowo wygladających kontenerów i komponentów:
            X #1. Zbudowanie w prawidłowy sposób kontenerów
            X #2. Zbudowanie w prawidłowy sposób komponentów
        X 5. Sprawdzic jak wygląda operowanie na podkomponentach z perspektywy nadkomponentów
        X 6. Gdy będzie wszystko gotowe usunąć Redux-a
        7. Uporządkować pliki i foldery
        8. Użyć sesji do logowania zalogowanego uzytkownika
        X 9. Zmienić i zapoznać sie z funkcją render
*/

const App = () => (
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
);

export default App;
