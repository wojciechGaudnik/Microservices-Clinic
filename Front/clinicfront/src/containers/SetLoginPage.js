import {connect} from "react-redux";
import LoginPage from "../components/LoginPage";
import {sendRequestByGivenDetails, setStoreUserDetails} from "../actions";
import {URLs} from "../URL's";

const mapDispatchToProps = dispatch => ({
    setStoreUserDetails: (userDetails) => {dispatch(setStoreUserDetails(userDetails))}
});

export default connect(
    null,
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
export const sendFetchRequestLoginUser = (email, password, {setUserDetails}) => {
    const body = {
        "email":email,
        "password":password
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
    )
};