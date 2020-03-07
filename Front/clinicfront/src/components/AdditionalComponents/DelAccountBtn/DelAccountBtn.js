import React from "react";

import {Button} from "react-bootstrap";

import {redirectByRole} from "../../../actions";

export const DelAccountBtn = (props) => {
    const delUserBtnOnClick = () => {
        props.fetchRequest();
        localStorage.removeItem("token");
        redirectByRole(null, props);
    };

    return(
        <Button variant="light" size="sm" block onClick={() => delUserBtnOnClick()}>
            Delete Account
        </Button>
    )
};