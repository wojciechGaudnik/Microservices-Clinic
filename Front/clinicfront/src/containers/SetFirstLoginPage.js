import {connect} from "react-redux";
import LoginPage from "../components/LoginPage";
import {setStoreUserDetails} from "../actions";

const mapDispatchToProps = dispatch => ({
        setStoreUserDetails: (userDetails) => {dispatch(setStoreUserDetails(userDetails))}
});

export default connect(
    null,
    mapDispatchToProps
)(LoginPage)