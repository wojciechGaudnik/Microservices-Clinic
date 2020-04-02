import {connect} from "react-redux";
import PatientPage from "./PatientPage";
import {setStoreError, setStorePatientInformation} from "../../../actions";
import error_401 from "../../../images/error_401.jpg";
import {URLs} from "../../../URLs";
import {sendRequestByGivenDetails} from "../../../actions/fetchRequest";

const getUserDetails = state => ( state.info.userDetails );
const getUserInformation = state => ( state.info.userInformation );
const getError = state => ( state.error );

const mapStateToProps = state => ({
    error: getError(state),
    userDetails: getUserDetails(state),
    userInformation: getUserInformation(state)
});

const mapDispatchToProps = dispatch => ({
    setStoreUserInformation: (userInformation) => {dispatch(setStorePatientInformation(userInformation))},
    setStoreError: (error) => {dispatch(setStoreError(error))}
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(PatientPage)

//CSS Stylesheet
export const styleForMainDiv = {
    margin: '30px'
};

export const styleForSubContainer = {
    color: 'black',
    padding: '10px 30px 10px 30px',
    marginBottom: '15px',
    width: '50%',
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

export const styleForBackToLoginPageButton = {
    marginTop: '10px',
    border: '2px solid black'
};

export const styleForMainContainer = {
    marginTop: "50px"
};

//Content for fetch request
export const sendFetchRequestSetUserInformation = (alreadyLoginUserUuid, setUserInformationInStateFunction, setStoreUserInformation, {ifCatchSetErrorInStore}) => {
    const url = URLs.GET_PATIENT_INFORMATION + alreadyLoginUserUuid;

    const body = null;

    const method = 'GET';

    const headers = {'Authorization': localStorage.token};

    const setInStateFunction = (responseData) => setUserInformationInStateFunction(responseData);

    const specialFunction = (responseData) => setStoreUserInformation(responseData);

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
    const url = URLs.CHANGE_PATIENT_INFORMATION + alreadyLoginUserUuid;

    const method = 'PATCH';

    const body = {
        "firstName" :newUserInformation.firstName,
        "lastName" :newUserInformation.lastName,
        "pesel" :newUserInformation.pesel,
        "photoUrl" :newUserInformation.photoUrl
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
    const url = URLs.DELETE_PATIENT + alreadyLoginUserUuid;

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