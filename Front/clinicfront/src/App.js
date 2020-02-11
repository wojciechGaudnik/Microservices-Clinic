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
        const URL = 'http://localhost:8762/auth/auth';        

        axios({
            url: 'http://localhost:8762/auth/auth',
            method: 'POST',            
            async: false,
            crossDomain: true,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            username: 'jan',
            password: '12345'
        }).then(respone => {console.log(respone.status)});
    }
    
    
    
    function getTokenByGivenLoginDetailsFetch(username = "jan", password = "12345"){
        const URL = 'http://localhost:8762/auth/auth';
        const user = {
            "username": "jan",
            "password": "12345"
        };
       
        fetch(URL, {
            method: 'POST',
            body: JSON.stringify(user)
        })
            .then(results => results.json())
            .then(results => {
                console.log("---> " + results.Authorization);
            });
    }

    function getInfoByGivenToken(){

    }
}

export default App;
