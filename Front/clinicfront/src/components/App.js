import React from 'react';
import {BrowserRouter} from 'react-router-dom';
import {Route, Switch} from "react-router";
import 'bootstrap/dist/css/bootstrap.min.css';
import DoctorPage from "./EmployeeComponents/DoctorPage/SetDoctorPage";
import PatientPage from "./EmployeeComponents/PatientPage/SetPatientPage";
import {AssistantPage} from "./EmployeeComponents/AssistantPage/AssistantPage";
import RegisterPage from "./MainComponents/RegisterPage/SetRegisterPage";
import LoginPage from "./MainComponents/LoginPage/SetLoginPage";

//TODO:Sprawdzanie już zalogowanego użytkownika

const App = () => (
    <BrowserRouter>
        <div className="App">
            <Switch>
                <Route exact path="/" render={props => <LoginPage {...props}/>}/>
                <Route path="/doctor" render={props => <DoctorPage {...props}/>}/>
                <Route path="/patient" render={props => <PatientPage {...props}/>}/>
                <Route path="/assistant" render={AssistantPage}/>
                <Route path="/register" render={props => <RegisterPage {...props}/>}/>
            </Switch>
        </div>
    </BrowserRouter>
);

export default App;
