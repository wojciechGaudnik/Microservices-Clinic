import React, {useEffect, useState} from "react";
import {redirectByRole} from "../actions";

export const LoginPage = (props) => {
    const [userDetails, setUserDetails] = useState({
        uuid: null,
        role: null
    });

    useEffect(() => {
        redirectByRole(userDetails.role, props)
    },[userDetails.role]);

    let email;
    let password;

    return  (
        <div>
            <form onSubmit= {e => {
                e.preventDefault();
                getTokenByGivenLoginDetails(email.value, password.value)
            }}>
                <label>Email: </label><input type="text" name="email" ref={input => email = input}/>
                <br/>
                <label>Password: </label><input type="text" name="password" ref={input => password = input}/>
                <br/>
                <input type="submit"/>
            </form>
            {/*<button onClick={() => getInfo({setUserDetails}, userDetails.uuid)}>CLICK</button>*/}
        </div>
    );

    function getTokenByGivenLoginDetails(email, password) {
        const URL = 'http://localhost:8762/auth/login';
        const user = {
            "email": email,
            "password": password
        };
        fetch(URL, {
            method: 'POST',
            async: false,
            body: JSON.stringify(user)
        })
            .then((response) => response.json())
            .then((responseData) => {
                console.log("Before : " + localStorage.token);
                setUserDetails({
                    uuid: responseData.uuid,
                    role: responseData.role
                });
                localStorage.setItem("token", responseData.token);
                console.log("After : " + localStorage.token);
            });
    }
};

export default LoginPage
