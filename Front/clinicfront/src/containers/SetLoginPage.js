import {connect} from "react-redux";
import LoginPage from "../components/LoginPage";
import {setStoreError, setStoreUserDetails} from "../actions";
import {sendRequestByGivenDetails} from "../actions/fetchRequest";
import {URLs} from "../URLs";

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
export const styleForFormLabel = {
    color:'white'
};

export const styleForForm = {
    border: '2px solid white',
    borderRadius: '5px',
    padding: '8px'
};

export const styleForMainDiv = {
    margin: '50px auto auto 50px',
    width: '30%',
};

export const styleForButton = {
    marginLeft: '0.5%',
    marginRight: '0.5%',
    width: '49%'
};

//Content for fetch request
export const sendFetchRequestLoginUser = (loginDetails, {setUserDetails}, {ifCatchSetErrorInStore}) => {
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
        URLs.LOGIN_USER,
        'POST',
        body,
        headers,
        setInStateFunction,
        specialFunction,
        ifCatchSetErrorInStore
    )
};

export const sendFetchRequestIsThereLoginUser = ({setUserDetails}) => {
    const body = null;

    const headers = {'Authorization': localStorage.token};

    const setInStateFunction = (responseData) => {
        console.log(responseData);
        setUserDetails({
            uuid: responseData.uuid,
            role: responseData.role
        })
    };

    const specialFunction = null;

    sendRequestByGivenDetails(
        URLs.GET_DETAILS_BY_TOKEN,
        'GET',
        body,
        headers,
        setInStateFunction,
        specialFunction
    )

};