import {connect} from "react-redux";
import LoginPage from "./LoginPage";
import {setStoreError, setStoreUserDetails} from "../../../actions";
import {sendRequestByGivenDetails} from "../../../actions/fetchRequest";
import {URLs} from "../../../URLs";

const getError = state => (state.error);

const mapStateToProps = state => ({
    error: getError(state)
});

const mapDispatchToProps = dispatch => ({
    setStoreUserDetails: (userDetails) => {dispatch(setStoreUserDetails(userDetails))},
    setStoreError: (error) => {dispatch(setStoreError(error))}
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(LoginPage)

//CSS Stylesheet

export const styleForButton = {
    marginLeft: '0.5%',
    marginRight: '0.5%',
    width: '49%'
};

//Content for fetch request
export const sendFetchRequestLoginUser = (loginDetails, {setUserDetails}, {ifCatchSetErrorInStore}) => {
    const url = URLs.LOGIN_USER;

    const method = 'POST';

    const body = {
        "email":loginDetails.email,
        "password":loginDetails.password
    };

    const headers = ({});

    const setInStateFunction = (responseData) => {
        setUserDetails({
            uuid: responseData.uuid,
            role: responseData.role
        })
    };

    const specialFunction = (responseData) => {
        localStorage.setItem("token", responseData.token)
    };

    sendRequestByGivenDetails(
        url,
        method,
        body,
        headers,
        setInStateFunction,
        specialFunction,
        ifCatchSetErrorInStore
    )
};

export const sendFetchRequestIsThereLoginUser = ({setUserDetails}) => {
    const url = URLs.GET_DETAILS_BY_TOKEN;

    const method = 'GET';

    const body = null;

    const headers = {'Authorization': localStorage.token};

    const setInStateFunction = (responseData) => {
        setUserDetails({
            uuid: responseData.uuid,
            role: responseData.role
        })
    };

    const specialFunction = null;

    sendRequestByGivenDetails(
        url,
        method,
        body,
        headers,
        setInStateFunction,
        specialFunction
    )

};