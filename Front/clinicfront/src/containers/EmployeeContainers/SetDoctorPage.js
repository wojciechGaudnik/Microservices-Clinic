import {connect} from "react-redux";
import DoctorPage from "../../components/EmployeeComponents/DoctorPage";
import {setStoreDoctorInformation, setStoreError} from "../../actions";
import {sendRequestByGivenDetails} from "../../actions/fetchRequest";
import {URLs} from "../../URLs";

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
    margin: '30px'
};

export const styleForSubContainer = {
    color: 'black',
    padding: '10px 30px 10px 30px',
    marginBottom: '15px',
    width: '50%',
};

//Content for fetch request
export const sendFetchRequestSetUserInformation = (alreadyLoginUserUuid, setUserInformationInStateFunction) => {
    const body = null;

    const headers = {'Authorization': localStorage.token};

    const setInStateFunction = (responseData) => setUserInformationInStateFunction(responseData);

    const specialFunction = null;

    sendRequestByGivenDetails(
        URLs.GET_USER_INFORMATION + alreadyLoginUserUuid,
        'GET',
        body,
        headers,
        setInStateFunction,
        specialFunction,
    )
};

export const sendFetchRequestChangeUserInformation = (newUserInformation, {ifCatchSetErrorInStore}, alreadyLoginUserUuid) => {
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
        URLs.CHANGE_DOCTOR_INFORMATION + alreadyLoginUserUuid,
        'PATCH',
        body,
        headers,
        setInStateFunction,
        specialFunction,
        ifCatchSetErrorInStore
    )
};

export const sendFetchRequestDeleteUser = ({uuid}) => {
    const body = null;

    const headers = {
        'Authorization': localStorage.token,
        'Content-Type': 'application/json',
    };

    const setInStateFunction = null;

    const specialFunction = null;

    sendRequestByGivenDetails(
        URLs.DELETE_DOCTOR + uuid,
        'DELETE',
        body,
        headers,
        setInStateFunction,
        specialFunction,
    )
};