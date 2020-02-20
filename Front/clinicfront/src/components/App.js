import React from 'react';
import { BrowserRouter} from 'react-router-dom';
import { Route, Switch } from "react-router";
import {DoctorPage} from "../containers/EmployeePage/DoctorPage";
import {PatientPage} from "../containers/EmployeePage/PatientPage";
import {AssistantPage} from "../containers/EmployeePage/AssistantPage";
import LoginPage from "./LoginPage";

const App = () => (
        <BrowserRouter>
            <div className="App">
                <Switch>
                    <Route exact path="/" render={props => <LoginPage {...props}/>}/>
                    <Route path="/doctor" render={DoctorPage}/>
                    <Route path="/patient" render={PatientPage}/>
                    <Route path="/assistant" render={AssistantPage}/>
                </Switch>
            </div>
        </BrowserRouter>
);

export default App;
