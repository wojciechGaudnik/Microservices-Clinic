import React, {useState} from 'react';
import axios from 'axios';

// If you want to run this application you must download axios by this command:
// npm install axios --save
function App() {
    const [message, setMessage] = useState(null);

    return(
        <div>
            <p>{message}</p>
            <button onClick={() => getTokenByGivenLoginDetailsFetch()}>
                Connect to get message
            </button>
            <br/>
            <br/>
            <br/>
            <button onClick={() => registerNewUserInAuth()}>
                Connect to get message
            </button>
        </div>
    );
    
    
    function getTokenByGivenLoginDetailsFetch(email = "jan", password = "12345"){
        const URL = 'http://localhost:8762/auth/login';
        const user = {
            "email": "ola@ola.pl",
            "password": "12345"
        }
        fetch(URL, {
            method: 'POST',
            async: false,
            body: JSON.stringify(user)
        })
            .then(results => results.json())
            .then(results => {
                console.log("---> " + results.token);
                const fetchData = fetch('http://localhost:8762/doctor-mssc/doctors', {
                    method: 'GET',
                    async: false,
                    headers: {
                    'Authorization': results.token
                    }
                });
                fetchData.then(resoults => {
                    return resoults.json();
                })
                    .then(resoult => {
                        console.log(resoult.message);
                    })

            });
    
    }


    function registerNewUserInAuth(){
        const URL = 'http://localhost:8762/auth/users/';
        const user = {
            "email": "pies@kot.pl",
            "password": "12345asdf",
            "role": "doctor"
        };
        fetch(URL, {
            method: 'POST',
            async: false,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
            .then(results => {
                return results.json();
            })
            .then(results => {
                console.log("test");
                console.log("---> " + results.token);
                let bodyToRegister = {
                    "doctoruuid": results.uuid,
                    "firstName": "Janusz",
                    "lastName": "Kowalski",
                    "photoUrl": "http://www.januszowo.com",
                    "licence": "111-222-333-444-555"
                };

                const fetchData = fetch('http://localhost:8762/doctor-mssc/doctors/', {
                    method: 'POST',
                    async: false,
                    headers: {
                        'Authorization': results.token,
                        'Content-Type': 'application/json;charset=UTF-8'
                    },
                    body: JSON.stringify(bodyToRegister)
                })
                .then(results => {
                    return results.json();
                })
                    .then(results => {
                        console.log(results.firstName);
                    })

            });

    }






}

export default App;
