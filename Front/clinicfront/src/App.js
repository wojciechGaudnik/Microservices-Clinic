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

}

export default App;
