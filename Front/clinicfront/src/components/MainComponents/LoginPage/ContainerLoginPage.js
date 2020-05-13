import React, {useEffect, useReducer} from "react";
import {URLs} from "../../../URLs";
import {useHistory} from "react-router";

const ContainerLoginPage = ({setStoreUserDetails, children}) => {

  //Here we create reducer
  const logInUser = (state, action) => {
    switch (action.type) {
      case "LOGIN_SUCCESS":
        return {
          ...state,
          uuid: action.fetchResults.userUUID,
          role: action.fetchResults.role
        };
      case "LOGIN_FAILED":
        return {
          ...state,
          isError: true
        };
      case "CHECK_LOGIN_USER_FAILED":
        return state;
      case "CLOSE_ERROR_MASSAGE":
        return {
          ...state,
          isError: false
        }
    }
  };
  const initialState = {
    uuid: null,
    role: null,
    isError: false
  };
  const init = (initialState) => initialState;
  const [userDetails, dispatchUserState] = useReducer(logInUser, initialState, init);

  //Here we have Effects
  let history = useHistory();
  useEffect(() => {
    const redirectAfterChangeRole = () => {
      if (userDetails.role){
        setStoreUserDetails(userDetails);
        history.push(`/${userDetails.role}`);
    }};
    redirectAfterChangeRole();
  }, [userDetails.role, userDetails, setStoreUserDetails]);

  useEffect(() => {
    const sendFetch = async () => {
      if (localStorage.token && !userDetails.role){
        try {
          const init = {
            method: 'GET',
            body: null,
            headers: {'Authorization': localStorage.token}
          };
          const response = await fetch(URLs.GET_DETAILS_BY_TOKEN, init);
          const result = await response.json();
          dispatchUserState({
            type: "LOGIN_SUCCESS",
            fetchResults: {
              userUUID: result.userUUID,
              role: result.role
            }
          })
        }catch (e) {
          dispatchUserState({type: "CHECK_LOGIN_USER_FAILED"})
        }
      }
    };
    sendFetch();
  }, [userDetails.role]);

  //Here we have additional methods
  const sendFetchForLoginUser = async (loginDetails) => {
    try {
      const body = JSON.stringify({
        "email":loginDetails.email,
        "password":loginDetails.password
      });
      const init = {
        method: 'POST',
        body: body,
        headers: {},
      };
      const response = await fetch(URLs.LOGIN_USER, init);
      const result = await response.json();
      localStorage.setItem("token", result.token);
      dispatchUserState({
        type: "LOGIN_SUCCESS",
        fetchResults: {
          userUUID: result.userUUID,
          role: result.role
        }
      });
    } catch (e) {
      dispatchUserState({type: "LOGIN_FAILED"});
    }
    console.log("hello")
  };

  return (
    children({
      userDetails,
      dispatchUserState,
      sendFetchForLoginUser
    })
  )
};

export default ContainerLoginPage;