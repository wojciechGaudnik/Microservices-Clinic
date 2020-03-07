import {Button} from "react-bootstrap";
import React from "react";
import {redirectByRole} from "../../../actions";

export const LogOutButton = (props) => {
    return (
        <Button onClick={() => {
            localStorage.removeItem("token");
            redirectByRole(null, props);
        }}>
            Log Out
        </Button>
    )
};