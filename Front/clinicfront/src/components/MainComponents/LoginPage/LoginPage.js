import React, {useEffect, useReducer} from "react";

import {redirectByRole} from "../../../actions";

import {FormForInputUserInformation} from "../../AdditionalComponents/FormForInputUserInfo/FormForInputUserInformation";
import {Container} from "@material-ui/core";
import AlertMessage from "../../AdditionalComponents/Alert/AlertMessage";
import {URLs} from "../../../URLs";

export const LoginPage = (props) => {

  const {
    setStoreUserDetails
  } = props;

  //Here we create reducer
  const checkLoginUser = (state, action) => {
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
  const [userDetails, dispatchUserState] = useReducer(checkLoginUser, initialState, init);

  useEffect(() => {
    setStoreUserDetails(userDetails);
    if (userDetails.role){redirectByRole(userDetails.role, props)}
  }, [userDetails.role, userDetails, props, setStoreUserDetails]);

  useEffect(() => {
    const sendFetch = async () => {
      if (localStorage.token && !userDetails.role){
        try {
          const response = await fetch(
            URLs.GET_DETAILS_BY_TOKEN,
            {
              method: 'GET',
              body: null,
              headers: {'Authorization': localStorage.token}
            }
          );
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

      const response = await fetch(
        URLs.LOGIN_USER,
        init
      );

      const result = await response.json();

      localStorage.setItem("token", result.token);
      dispatchUserState({
        type: "LOGIN_SUCCESS",
        fetchResults: {
          userUUID: result.userUUID,
          role: result.role
        }
      })
    } catch (e) {
      dispatchUserState({type: "LOGIN_FAILED"});
    }
  };

  //Main HTML return
  return (
    <Container>
      <AlertMessage
        show={userDetails.isError}
        onClose={() => {dispatchUserState({type: "CLOSE_ERROR_MASSAGE"})}}
        message="Wrong Login Details"
        type="error"
      />
      <FormForInputUserInformation
        {...props}
        fetchRequest        ={(loginDetails) => {sendFetchForLoginUser(loginDetails)}}
        submitButtonTitle   ="Log In"
        showEmailForm       ={true}
        showPasswordForm    ={true}
        showRoleForm        ={false}
        showFirstNameForm   ={false}
        showLastNameForm    ={false}
        showLicenceForm     ={false}
        showPhotoURLForm    ={false}
      />
    </Container>
  );
};

export default LoginPage
