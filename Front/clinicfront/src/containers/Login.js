import {setMessage} from "../actions";
import { connect } from 'react-redux'
import React from "react";

export const Login = ({ dispatch }) => {
    let username;
    let password;

    return  (
        <form onSubmit= {e => {
            e.preventDefault();
            getTokenByGivenLoginDetails(username.value, password.value)
        }}>
            <label>Login: </label><input type="text" name="username" ref={input => username = input}/>
            <br/>
            <label>Password: </label><input type="text" name="password" ref={input => password = input}/>
            <br/>
            <input type="submit"/>
        </form>
    );

    function getTokenByGivenLoginDetails(username, password) {
        const URL = 'http://localhost:8762/auth/login';
        const user = {
            "username": username,
            "password": password
        };
        console.log(user);
        fetch(URL, {
            method: 'POST',
            async: false,
            body: JSON.stringify(user)
        })
            .then((response) => response.json())
            .then((responseData) => {
                getInfoByToken(responseData.Authorization);
            });
    }

    function getInfoByToken(token){
        const fetchData = fetch('http://localhost:8762/doctor/doctors', {
            method: 'GET',
            async: false,
            headers: {
                'Authorization': token,
            }
        });

        fetchData
            .then(results => {
                return results.json();
            })
            .then(result => {
                dispatch(setMessage(result.message))
            })
    }
};

export default connect()(Login)
