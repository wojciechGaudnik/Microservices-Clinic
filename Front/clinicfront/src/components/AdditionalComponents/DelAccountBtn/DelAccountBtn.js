import React from "react";

import {Button} from "@material-ui/core";

export const DelAccountBtn = ({fetchRequest}) => {
    const delUserBtnOnClick = () => {
        fetchRequest();
    };

    return(
        <Button
          variant="contained"
          color="primary"
          disableElevation
          fullWidth
          onClick={() => delUserBtnOnClick()}
        >
            Delete Account
        </Button>
    )
};