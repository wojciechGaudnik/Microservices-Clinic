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
        <Button variant="contained" style={{backgroundColor: "#4d1919", color: "white"}} disableElevation onClick={() => logOutButtonClick()}>
            Log Out
        </Button>
    )
};