import {connect} from "react-redux";
import DoctorPage from "../DoctorPage";
import {setStoreUserInformation} from "../../../actions";

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