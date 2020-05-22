import React from "react";

import {Button} from "@material-ui/core";

export const DelAccountBtn = ({fetchRequest}) => {
    const delUserBtnOnClick = () => {
        fetchRequest();
    };

    return(
        <Button variant="contained" style={{backgroundColor: "#4d1919", color: "white"}} disableElevation fullWidth onClick={() => delUserBtnOnClick()}>
            Delete Account
        </Button>
    )
};