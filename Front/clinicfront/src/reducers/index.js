import { combineReducers } from "redux";
import info from "./info";
import error from "./error";

export default combineReducers({
    info,
    error,
});