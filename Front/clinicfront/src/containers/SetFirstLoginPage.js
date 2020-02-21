import {connect} from "react-redux";
import LoginPage from "../components/LoginPage";
import {setStoreUserDetails} from "../actions";

const getMessage = state => ( state.info.userDetails );

const mapStateToProps = state => ({
    result: getMessage(state),
});

const mapDispatchToProps = dispatch => ({
        setStoreUserDetails: (userDetails) => {dispatch(setStoreUserDetails(userDetails))}
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(LoginPage)