import React from "react";

import {Button} from "@material-ui/core";
import {useHistory} from "react-router";

export const LogOutButton = () => {
    const history = useHistory();
    const logOutButtonClick = () => {
        localStorage.removeItem("token");
        history.push("/");
    };

    return (
        <Button variant="contained" color="primary" disableElevation onClick={() => logOutButtonClick()}>
            Log Out
        </Button>
    )
};