import {Typography} from "@material-ui/core";
import {withStyles} from "@material-ui/styles";

export const StylesTabTypography = withStyles({
  button: {
    color: "white",
    fontSize: "1.05rem",
    height: "26px"
  },
  caption: {
    color: "#e6e6e6",
    fontSize: "0.7rem"
  }
})(Typography)

export const StylesButtonTypography = withStyles({
  button: {
    color: "white",
    fontSize: "0.9rem",
    height: "26px"
  },
  caption: {
    color: "#e6e6e6",
    fontSize: "0.6rem"
  }
})(Typography)