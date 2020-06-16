import {StylesButtonTypography} from "./StylesTypography";
import React from "react";
import {Grid} from "@material-ui/core";

const ButtonCustomTypography = (props) => {

  const {
    primaryLabel,
    secondaryLabel
  } = props;

  return (
    <Grid
      container
      spacing={1}
      direction="row"
      justify="center"
      alignItems="center"
    >
      <Grid item>
      <StylesButtonTypography variant={"button"} noWrap>
        {primaryLabel}
      </StylesButtonTypography>
      </Grid>
      <Grid item>
      <StylesButtonTypography variant={"caption"} noWrap>
        ({secondaryLabel})
      </StylesButtonTypography>
      </Grid>
    </Grid>
  )
}

export default ButtonCustomTypography