import React from "react";

import {Button} from "@material-ui/core";
import ButtonCustomTypography from "../CustomTypography/ButtonCustomTypography";

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
      <ButtonCustomTypography
        primaryLabel={"Usuń konto"}
        secondaryLabel={"Delete account"}
      />
    </Button>
  )
};