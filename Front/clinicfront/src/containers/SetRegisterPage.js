import {sendRequestByGivenDetails} from "../actions/fetchRequest";
import {URLs} from "../URLs";
import {setStoreError, setStoreUserDetails} from "../actions";
import {connect} from "react-redux";
import RegisterPage from "../components/RegisterPage";

const getError = state => (state.error);

const mapStateToProps = state => ({
    error: getError(state)
});

const mapDispatchToProps = dispatch => ({
    setStoreError: (error) => {dispatch(setStoreError(error))}
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(RegisterPage)

//CSS Stylesheet
export const styleForMainDiv = {
    margin: '50px auto auto 50px',
    width: '60%',
};

//Content for fetch request
export const sendFetchRequestRegisterNewDoctor = (userRegisterInformation, {ifCatchSetErrorInStore}) => {
    const body = {
        "email": userRegisterInformation.email,
        "password": userRegisterInformation.password,
        "role": userRegisterInformation.role
    };

    const headers = {
        'Content-Type': 'application/json'
    };

    const setInStateFunction = null;

    const specialFunction = (responseJSONData) => {
        const body = {
            doctoruuid: responseJSONData.uuid,
            firstName: userRegisterInformation.firstName,
            lastName: userRegisterInformation.lastName,
            photoUrl: userRegisterInformation.photoURL,
            licence: userRegisterInformation.licence
        };

        const headers = {
            'Authorization': responseJSONData.token,
            'Content-Type': 'application/json;charset=UTF-8',
        };

        const setInStateFunction = null;

        const specialFunction = null;

        sendRequestByGivenDetails(
            URLs.REGISTER_DOCTOR,
            'POST',
            body,
            headers,
            setInStateFunction,
            specialFunction,
            ifCatchSetErrorInStore
        );
    };

    sendRequestByGivenDetails(
        URLs.REGISTER_USER,
        'POST',
        body,
        headers,
        setInStateFunction,
        specialFunction,
        ifCatchSetErrorInStore
    );
};