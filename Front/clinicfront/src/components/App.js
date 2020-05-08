import React from 'react';
import {BrowserRouter} from 'react-router-dom';
import {Route, Switch} from "react-router";
import 'bootstrap/dist/css/bootstrap.min.css';
import DoctorPage from "./EmployeeComponents/DoctorPage/SetDoctorPage";
import PatientPage from "./EmployeeComponents/PatientPage/SetPatientPage";
import {AssistantPage} from "./EmployeeComponents/AssistantPage/AssistantPage";
import {MainPage} from "./MainComponents/MainPage/MainPage";

/*
    TODO:
        X 1. Zrefaktorować zapytania fetchowe użyć useReducers
        X 2. Zrefaktorować useEffects
        X 3. Zrefaktorować useState na obiekty a nie osobne stany
        X 4. Zbudowanie prawidłowo wygladających kontenerów i komponentów:
            X #1. Zbudowanie w prawidłowy sposób kontenerów
            X #2. Zbudowanie w prawidłowy sposób komponentów
        5. Sprawdzic jak wygląda operowanie na podkomponentach z perspektywy nadkomponentów
        6. Gdy będzie wszystko gotowe usunąć Redux-a
        7. Uporządkować pliki i foldery
        8. Użyć sesji do logowania zalogowanego uzytkownika
        9. Zmienić i zapoznać sie z funkcją render
*/

const App = () => (
    <BrowserRouter>
        <div className="App">
            <Switch>
                <Route exact path="/">
                  <MainPage/>
                </Route>
                <Route path="/doctor">
                  <DoctorPage/>
                </Route>
                <Route path="/patient">
                  <PatientPage/>
                </Route>
                <Route path="/assistant">
                  <AssistantPage/>
                </Route>
            </Switch>
        </div>
    </BrowserRouter>
);

export default App;
