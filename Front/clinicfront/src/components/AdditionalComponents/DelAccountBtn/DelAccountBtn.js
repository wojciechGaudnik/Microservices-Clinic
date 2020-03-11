import React from "react";

import {Button} from "@material-ui/core";

import {redirectByRole} from "../../../actions";

export const DelAccountBtn = (props) => {
    const { fetchRequest } = props;

    const delUserBtnOnClick = () => {
        fetchRequest();
        localStorage.removeItem("token");
        redirectByRole(null, props);
    };

    return(
        <Button variant="contained" color="primary" disableElevation fullWidth onClick={() => delUserBtnOnClick()}>
            Delete Account
        </Button>
    )
};