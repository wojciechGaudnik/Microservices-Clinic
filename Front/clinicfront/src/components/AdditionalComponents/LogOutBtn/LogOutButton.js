import React from "react";

import {Button} from "@material-ui/core";
import {redirectByRole} from "../../../actions";

export const LogOutButton = (props) => {
    const logOutButtonClick = () => {
        localStorage.removeItem("token");
        redirectByRole(null, props);
    };

    return (
        <Button variant="contained" color="primary" disableElevation onClick={() => logOutButtonClick()}>
            Log Out
        </Button>
    )
};