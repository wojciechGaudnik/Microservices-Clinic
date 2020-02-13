import React, {useEffect, useState} from 'react';
import axios from 'axios';

// If you want to run this application you must download axios by this command:
// npm install axios --save
function App() {
    const [message, setMessage] = useState(null);

    useEffect(() => {
        const connect = async () => {
            const URL = 'http://localhost:8762/auth';
            const user = "jan";
            const pass = "12345";
            try {
                const response = await axios({
                    method: 'POST',
                    url: URL,
                    async: true,
                    crossDomain: true,
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Access-Control-Allow-Origin': '*'
                    },
                    body: {
                        "username": user,
                        "password": pass
                    }
                }).then(respone => {
                    console.log(respone.status)
                });
            } catch (e) {
                console.log(e);
            }
        };
        connect();
    }, []);

    return(
        <div>
            <button onClick={() => connectWithDoctors()}>Patient</button>
            <button>Doctor</button>
            <button>Assistan</button>
        </div>
    );

    function connectWithDoctors() {
        try {
            // let givenMessage = getInfoByGivenToken();
            // setMessage(tokenForLogin);
        }catch (e) {
            console.log(e);
            setMessage("Nothing");
        }
    }




    // async function getTokenByGivenLoginDetails(
    //     user = 'jan',
    //     pass = '12345'
    // ){
    //     const URL = 'http://localhost:8762/auth';
    //     const response = await axios({
    //         method: 'POST',
    //         url: URL,
    //         async: true,
    //         crossDomain: true,
    //         headers: {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json;charset=UTF-8',
    //             'Access-Control-Allow-Origin': '*'
    //         },
    //         body: {
    //             "username": user,
    //             "password": pass
    //         }
    //     }).then(respone => {console.log(respone.status)});
    // }

    // async function getTokenByGivenLoginDetails(
    //     user = 'jan',
    //     pass = '12345'
    // ){
    //     const response = fetch('http://localhost:8762/auth', {
    //         method: 'POST',
    //         headers: {
    //             "Access-Control-Allow-Origin": "*"
    //         },
    //         body: {
    //             "username": user,
    //             "password": pass
    //         }
    //     });
    //     console.log(response);
    // }
    //
    // function getInfoByGivenToken(){
    //
    // }
}

export default App;
