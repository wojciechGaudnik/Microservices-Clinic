import React, {useState} from 'react';
import axios from 'axios';

// If you want to run this application you must download axios by this command:
// npm install axios --save
function App() {
    const [message, setMessage] = useState(null);

    return(
        <div>
            <p>{message}</p>
            <button onClick={() => connectWithDoctors()}>
                Connect to get message
            </button><br/><br/><br/>
            <button onClick={() => getTokenByGivenLoginDetailsFetch()}>
                Connect to get message
            </button>
        </div>
    );

    function connectWithDoctors() {
        try {
            getTokenByGivenLoginDetails();
            // let givenMessage = getInfoByGivenToken();
            // setMessage(tokenForLogin);
        }catch (e) {
            console.log(e);
            setMessage("Nothing");
        }
    }

    function getTokenByGivenLoginDetails(username = "jan", password = "12345"){
        const URL = 'http://localhost:8762/auth/login';        

        axios({
            url: 'http://localhost:8762/auth/login',
            method: 'POST',            
            async: false,
            crossDomain: true,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            username: 'jan@jan.pl',
            password: '12345'
        }).then(respone => {console.log(respone.status)});
    }
    
    
    
    function getTokenByGivenLoginDetailsFetch(username = "jan", password = "12345"){
        const URL = 'http://localhost:8762/auth/login';
        const user = {
            "username": "jan@jan.pl",
            "password": "12345"
        };
        fetch(URL, {
            method: 'POST',
            async: false,
            body: JSON.stringify(user)
        })
            .then(results => results.json())
            .then(results => {
                console.log("---> " + results.Authorization);
                const fetchData = fetch('http://localhost:8762/doctor/doctors', {
                    method: 'GET',
                    async: false,
                    headers: {
                    'Authorization': results.Authorization
                    }
                });
                fetchData.then(resoults => {
                    return resoults.json();
                })
                    .then(resoult => {
                        console.log(resoult.message);
                    })
                // .then(resoults => JSON.parse(results))
                // .then(results => {
                //     console.log("---> " + results);
                // });
                // fetchData.then(resoults => {console.log(resoults)})
                    // .then(resoults => JSON.parse(results))
                    // .then(results => {
                    //     console.log("---> " + results);
                    // });

            });
    }
    

    function getInfoByGivenToken(){

    }
}

export default App;
