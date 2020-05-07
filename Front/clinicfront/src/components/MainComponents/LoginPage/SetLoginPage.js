import {connect} from "react-redux";
import LoginPage from "./LoginPage";
import {setStoreError, setStoreUserDetails} from "../../../actions";

const getError = state => (state.error);

const mapStateToProps = state => ({
    error: getError(state)
});

const mapDispatchToProps = dispatch => ({
    setStoreUserDetails: (userDetails) => {dispatch(setStoreUserDetails(userDetails))},
    setStoreError: (error) => {dispatch(setStoreError(error))}
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(LoginPage)

//CSS Stylesheet