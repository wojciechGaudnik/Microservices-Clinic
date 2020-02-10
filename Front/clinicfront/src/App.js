import React, {useState} from 'react';

function App() {
    const [message, setMessage] = useState(null);

    return(
        <div>
            <p>{message}</p>
            <button onClick={() => setMessage(connectWithDoctors())}>Connect to get message</button>
        </div>
    )

    function connectWithDoctors() {
        return "Nothing";
    }
}

export default App;
