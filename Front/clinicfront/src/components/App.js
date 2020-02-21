import React from 'react';
import {BrowserRouter} from 'react-router-dom';
import {Route, Switch} from "react-router";
import DoctorPage from "../containers/EmployeePage/EmployeeContainers/SetDoctorPage";
import {PatientPage} from "../containers/EmployeePage/PatientPage";
import {AssistantPage} from "../containers/EmployeePage/AssistantPage";
import LoginPage from "../containers/SetFirstLoginPage";

//TODO:Sprawdzanie już zalogowanego użytkownika
//TODO:Stworzneie połączenia umozliwiającego pobranie danych o użytkownaiku
//TODO:Wyświetlenie danych o użytkowaniu na stronie zaleznej od roli
//TODO:Wygląd stron pracowników

const App = () => (
    <BrowserRouter>
        <div className="App">
            <Switch>
                <Route exact path="/" render={props => <LoginPage {...props}/>}/>
                <Route path="/doctor" render={() => <DoctorPage/>}/>
                <Route path="/patient" render={PatientPage}/>
                <Route path="/assistant" render={AssistantPage}/>
            </Switch>
        </div>
    </BrowserRouter>
);

export default App;
