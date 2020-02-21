import {connect} from "react-redux";
import DoctorPage from "../DoctorPage";

const getUserDetails = state => ( state.info.userDetails );

const mapStateToProps = state => ({
    userDetails: getUserDetails(state),
});

export default connect(
    mapStateToProps,
)(DoctorPage)