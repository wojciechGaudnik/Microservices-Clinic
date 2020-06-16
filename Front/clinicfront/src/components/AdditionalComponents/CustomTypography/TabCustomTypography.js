import React from "react";
import {StylesTabTypography} from "./StylesTypography";

const TabCustomTypography = (props) => {

  const {
    primaryLabel,
    secondaryLabel
  } = props;

  return (
    <>
      <StylesTabTypography variant={"button"} noWrap>
        {primaryLabel}
      </StylesTabTypography>
      <StylesTabTypography variant={"caption"} noWrap>
        ({secondaryLabel})
      </StylesTabTypography>
    </>
  )
}

export default TabCustomTypography