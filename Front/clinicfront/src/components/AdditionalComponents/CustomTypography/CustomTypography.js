import React from "react";
import {StylesTypography} from "./StylesTypography";

const CustomTypography = (props) => {

  const {
    primaryLabel,
    secondaryLabel
  } = props;

  return (
    <>
      <StylesTypography variant={"button"} noWrap>
        {primaryLabel}
      </StylesTypography>
      <StylesTypography variant={"caption"} noWrap>
        ({secondaryLabel})
      </StylesTypography>
    </>
  )
}

export default CustomTypography