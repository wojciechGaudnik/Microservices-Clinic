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

//Content for fetch request
export const sendFetchRequestSetUserInformation = (uuid, {setUserInformation}) => {
    const body = null;

    const headers = {'Authorization': localStorage.token};

    const setInStateFunction = (responseData) => setUserInformation(responseData);

    const specialFunction = null;

    sendRequestByGivenDetails(
        URLs.GET_USER_INFORMATION + uuid,
        'GET',
        body,
        headers,
        setInStateFunction,
        specialFunction,
    )
};

export const sendFetchRequestChangeUserInformation = (newUserInformation, {ifCatchSetErrorInStore}, {uuid}) => {
    const body = {
        "email":      newUserInformation.email,
        "password":   newUserInformation.password,
        "firstName":  newUserInformation.firstName,
        "lastName":   newUserInformation.lastName,
        "photoUrl":   newUserInformation.photoURL,
        "licence":    newUserInformation.licence,
    };

    console.log(body);
    console.log(localStorage.token);
    console.log(uuid);

    const headers = {
        'Authorization': localStorage.token,
        'Content-Type': 'application/json'
    };

    const setInStateFunction = null;

    const specialFunction = null;

    sendRequestByGivenDetails(
        URLs.CHANGE_DOCTOR_INFORMATION + uuid,
        'PATCH',
        body,
        headers,
        setInStateFunction,
        specialFunction,
        ifCatchSetErrorInStore
    )
};

export const sendFetchRequestDeleteUser = () => {
    // const body = null;
    //
    // const headers = {};
    //
    // const setInStateFunction = null;
    //
    // const specialFunction = null;
    //
    // sendRequestByGivenDetails(
    //     URLs.GET_USER_INFORMATION,
    //     'GET',
    //     body,
    //     headers,
    //     setInStateFunction,
    //     specialFunction,
    // )
};