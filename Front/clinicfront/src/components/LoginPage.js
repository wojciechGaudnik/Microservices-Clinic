import React, {useEffect, useState} from "react";
import {getTokenByGivenLoginDetails, redirectByRole} from "../actions";

export const LoginPage = (props) => {
    const [userDetails, setUserDetails] = useState({
        uuid: null,
        role: null
    });

    let email;
    let password;

    useEffect(() => {
        props.setStoreUserDetails(userDetails);
        redirectByRole(userDetails.role, props)
    }, [userDetails.role]);

    return (
        <div>
            <form onSubmit={e => {
                e.preventDefault();
                getTokenByGivenLoginDetails(email.value, password.value, {setUserDetails})
            }}>
                <label>Email: </label><input type="text" name="email" ref={input => email = input}/>
                <br/>
                <label>Password: </label><input type="text" name="password" ref={input => password = input}/>
                <br/>
                <input type="submit"/>
            </form>
        </div>
    );
};

export default LoginPage
