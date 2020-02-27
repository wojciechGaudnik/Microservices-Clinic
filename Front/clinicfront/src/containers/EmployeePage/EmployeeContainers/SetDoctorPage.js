import {connect} from "react-redux";
import DoctorPage from "../DoctorPage";
import {sendRequestByGivenDetails, setStoreUserInformation} from "../../../actions";
import {URLs} from "../../../URL's";

const getUserDetails = state => ( state.info.userDetails );
const getUserInformation = state => ( state.info.userInformation );

const mapStateToProps = state => ({
    userDetails: getUserDetails(state),
    userInformation: getUserInformation(state)
});

const mapDispatchToProps = dispatch => ({
    setStoreUserInformation: (userInformation) => {dispatch(setStoreUserInformation(userInformation))}
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(DoctorPage)


//CSS stylesheet
export const styleForKeyCol = {
    textAlign: 'right',
    border: '2px solid grey',
    borderRadius: '5px',
    background: '#cce6ff'
};

export const styleForValueCol = {
    marginLeft: '3px',
    border: '2px solid grey',
    borderRadius: '5px',
    background: '#cce6ff'
};

export const styleForRow = {
    marginBottom: '5px'
};

export const styleForContainer = {
    color: 'black',
    padding: '10px 30px 10px 30px',
    width: '50%',
    border: '2px solid white',
    borderRadius: '7px',

};

export const styleForMainDiv = {
    margin: '30px'
};

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