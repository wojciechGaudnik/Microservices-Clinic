import {Button} from "react-bootstrap";
import React from "react";
import {redirectByRole} from "../../../actions";

export const LogOutButton = (props) => {
    const logOutButtonClick = () => {
        localStorage.removeItem("token");
        redirectByRole(null, props);
    };

    return (
        <Button onClick={() => logOutButtonClick()}>
            Log Out
        </Button>
    )
};