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
            </button>
        </div>
    );

    function connectWithDoctors() {
        try {


            setMessage("Nothing");
        }catch (e) {
            console.log(e);
            setMessage("Nothing");
        }
    }

    function getTokenByGivenData(){

    }

    function getInfoByGivenToken(){

    }
}

export default App;
