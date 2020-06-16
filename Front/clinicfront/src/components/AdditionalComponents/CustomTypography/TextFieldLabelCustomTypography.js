import {StylesTextFieldLabelTypography} from "./StylesTypography";
import React from "react";
import {Grid} from "@material-ui/core";

const TextFieldCustomTypography = (props) => {

  const {
    primaryLabel,
    secondaryLabel
  } = props;

  return (
    <Grid
      container
      spacing={1}
      direction="row"
      justify="flex-start"
      alignItems="center"
    >
      <Grid item>
        <StylesTextFieldLabelTypography variant={"h4"} noWrap>
          {primaryLabel}
        </StylesTextFieldLabelTypography>
      </Grid>
      <Grid item>
        <StylesTextFieldLabelTypography variant={"h2"} noWrap>
          ({secondaryLabel})
        </StylesTextFieldLabelTypography>
      </Grid>
    </Grid>
  )
}

export default TextFieldCustomTypography