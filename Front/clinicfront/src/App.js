import React, {useState} from 'react';
import axios from 'axios';

// If you want to run this application you must download axios by this command:
// npm install axios --save
function App() {
    const [message, setMessage] = useState(null);

    return(
        <div>
            <button></button>
            <button></button>
            <button></button>
        </div>
    );

    // function connectWithDoctors() {
    //     try {
    //         getTokenByGivenLoginDetails();
    //         // let givenMessage = getInfoByGivenToken();
    //         // setMessage(tokenForLogin);
    //     }catch (e) {
    //         console.log(e);
    //         setMessage("Nothing");
    //     }
    // }
    //
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
    //             username: user,
    //             password: pass
    //         }
    //     }).then(respone => {console.log(respone.status)});
    // }
    //
    // function getInfoByGivenToken(){
    //
    // }
}

export default App;
