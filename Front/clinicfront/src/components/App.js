import React from 'react';
import {BrowserRouter} from 'react-router-dom';
import {Route, Switch} from "react-router";
import 'bootstrap/dist/css/bootstrap.min.css';
import DoctorPage from "../containers/EmployeePage/EmployeeContainers/SetDoctorPage";
import {PatientPage} from "../containers/EmployeePage/PatientPage";
import {AssistantPage} from "../containers/EmployeePage/AssistantPage";
import RegisterPage from "./RegisterPage";
import LoginPage from "../containers/SetLoginPage";

//TODO:Sprawdzanie już zalogowanego użytkownika

//TODO: hooki
//TODO: browserRouter
//TODO: redux

const App = () => (
    <BrowserRouter>
        <div className="App">
            <Switch>
                <Route exact path="/" render={props => <LoginPage {...props}/>}/>
                <Route path="/doctor" render={() => <DoctorPage/>}/>
                <Route path="/patient" render={PatientPage}/>
                <Route path="/assistant" render={AssistantPage}/>
                <Route path="/register" render={() => <RegisterPage/>}/>
            </Switch>
        </div>
    </BrowserRouter>
);

export default App;
