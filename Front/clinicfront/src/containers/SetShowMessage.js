import { connect } from 'react-redux'
import ShowMassage from "../components/ShowMassage";
import { setMessage } from "../actions";

const getMessage = (message) => {
    return message;
};

const mapStateToProps = state => ({
    result: getMessage(state).info
});

const mapDispatchToProps = dispatch => ({
    onClickSetResult: message => dispatch(setMessage(message))
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(ShowMassage)

