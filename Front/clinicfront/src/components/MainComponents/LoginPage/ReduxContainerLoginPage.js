import {setStoreError, setStoreUserDetails} from "../../../actions";
import {connect} from "react-redux";
import ContainerLoginPage from "./ContainerLoginPage";

const mapStateToProps = state => ({
  error: state.error
});

const mapDispatchToProps = dispatch => ({
  setStoreUserDetails: (userDetails) => {dispatch(setStoreUserDetails(userDetails))},
  setStoreError: (error) => {dispatch(setStoreError(error))}
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ContainerLoginPage)