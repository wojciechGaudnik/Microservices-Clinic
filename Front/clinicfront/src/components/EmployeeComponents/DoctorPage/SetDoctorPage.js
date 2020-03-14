import {connect} from "react-redux";
import DoctorPage from "./DoctorPage";
import {setStoreDoctorInformation, setStoreError} from "../../../actions";
import {sendRequestByGivenDetails} from "../../../actions/fetchRequest";
import {URLs} from "../../../URLs";
import error_401 from "../../../images/error_401.jpg"

const getUserDetails = state => ( state.info.userDetails );
const getUserInformation = state => ( state.info.userInformation );
const getError = state => ( state.error );

const mapStateToProps = state => ({
    error: getError(state),
    userDetails: getUserDetails(state),
    userInformation: getUserInformation(state)
});

const mapDispatchToProps = dispatch => ({
    setStoreUserInformation: (userInformation) => {dispatch(setStoreDoctorInformation(userInformation))},
    setStoreError: (error) => {dispatch(setStoreError(error))}
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(DoctorPage)

//CSS Stylesheet
export const styleForMainDiv = {
    margin: '30px',
    width: '100%'
};

export const styleForMainDivError = {
    margin: '0px',
    textAlign: 'center',
    width: '100vw',
    height: '100vh',
    backgroundImage: `url(${error_401})`,
    backgroundPosition: 'center',
    backgroundRepeat: 'no-repeat',
    backgroundSize: 'cover',
};

export const styleForMainGrid = {
    marginBottom: '15px',
    marginRight: '25%',
    marginLeft: '25%',
    width: '50%'
};

export const styleForBackToLoginPageButton = {
    marginTop: '10px',
    border: '2px solid black'
};

//Content for fetch request
export const sendFetchRequestSetUserInformation = (alreadyLoginUserUuid, setUserInformationInStateFunction, {ifCatchSetErrorInStore}) => {
    const url = URLs.GET_USER_INFORMATION + alreadyLoginUserUuid;

    const method = 'GET';

    const body = null;

    const headers = {'Authorization': localStorage.token};

    const setInStateFunction = (responseData) => setUserInformationInStateFunction(responseData);

    const specialFunction = null;

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

export const sendFetchRequestChangeUserInformation = (newUserInformation, {ifCatchSetErrorInStore}, alreadyLoginUserUuid) => {
    const url = URLs.CHANGE_DOCTOR_INFORMATION + alreadyLoginUserUuid;

    const method = 'PATCH';

    const body = {
        "email" :newUserInformation.email,
        "password" :newUserInformation.password,
        "firstName" :newUserInformation.firstName,
        "lastName" :newUserInformation.lastName,
        "photoUrl" :newUserInformation.photoUrl,
        "licence" :newUserInformation.licence,
    };

    const headers = {
        'Authorization': localStorage.token,
        'Content-Type': 'application/json',
    };

    const setInStateFunction = null;

    const specialFunction = null;

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

export const sendFetchRequestDeleteUser = (alreadyLoginUserUuid) => {
    const url = URLs.DELETE_DOCTOR + alreadyLoginUserUuid;

    const method = 'DELETE';

    const body = null;

    const headers = {
        'Authorization': localStorage.token,
        'Content-Type': 'application/json',
    };

    const setInStateFunction = null;

    const specialFunction = null;

    sendRequestByGivenDetails(
        url,
        method,
        body,
        headers,
        setInStateFunction,
        specialFunction,
    )
};