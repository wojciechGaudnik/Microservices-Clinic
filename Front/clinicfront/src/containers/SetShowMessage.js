import { connect } from 'react-redux'
import ShowMassage from "../components/ShowMassage";

const getMessage = (message) => {
    return message;
};

const mapStateToProps = state => ({
    result: getMessage(state).info
});

export default connect(
    mapStateToProps,
)(ShowMassage)

