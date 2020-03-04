import React from 'react';
import {BrowserRouter} from 'react-router-dom';
import {Route, Switch} from "react-router";
import 'bootstrap/dist/css/bootstrap.min.css';
import DoctorPage from "../containers/EmployeeContainers/SetDoctorPage";
import PatientPage from "../containers/EmployeeContainers/SetPatientPage";
import {AssistantPage} from "./EmployeeComponents/AssistantPage";
import RegisterPage from "../containers/SetRegisterPage";
import LoginPage from "../containers/SetLoginPage";

//TODO:Sprawdzanie już zalogowanego użytkownika

const App = () => (
    <BrowserRouter>
        <div className="App">
            <Switch>
                <Route exact path="/" render={props => <LoginPage {...props}/>}/>
                <Route path="/doctor" render={() => <DoctorPage/>}/>
                <Route path="/patient" render={() => <PatientPage/>}/>
                <Route path="/assistant" render={AssistantPage}/>
                <Route path="/register" render={props => <RegisterPage {...props}/>}/>
            </Switch>
        </div>
    </BrowserRouter>
);

export default App;
