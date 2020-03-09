import React from 'react';
import {BrowserRouter} from 'react-router-dom';
import {Route, Switch} from "react-router";
import 'bootstrap/dist/css/bootstrap.min.css';
import DoctorPage from "./EmployeeComponents/DoctorPage/SetDoctorPage";
import PatientPage from "./EmployeeComponents/PatientPage/SetPatientPage";
import {AssistantPage} from "./EmployeeComponents/AssistantPage/AssistantPage";
import {MainPage} from "./MainComponents/MainPage/MainPage";

//TODO:Sprawdzanie już zalogowanego użytkownika

const App = () => (
    <BrowserRouter>
        <div className="App">
            <Switch>
                <Route exact path="/" render={props => <MainPage {...props}/>}/>
                <Route path="/doctor" render={props => <DoctorPage {...props}/>}/>
                <Route path="/patient" render={props => <PatientPage {...props}/>}/>
                <Route path="/assistant" render={AssistantPage}/>
            </Switch>
        </div>
    </BrowserRouter>
);

export default App;
