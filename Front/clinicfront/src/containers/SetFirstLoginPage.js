import {connect} from "react-redux";
import LoginPage from "../components/LoginPage";

const getMessage = state => ( state.info.message );

const mapStateToProps = state => ({
    result: getMessage(state),
});

export default connect(
    mapStateToProps
)(LoginPage)