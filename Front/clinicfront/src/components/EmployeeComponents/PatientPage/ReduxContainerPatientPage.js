import {connect} from "react-redux";
import {setStoreError, setStorePatientInformation} from "../../../redux/actions";
import {ContainerPatientPage} from "./ContainerPatientPage";

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
)(ContainerPatientPage)
