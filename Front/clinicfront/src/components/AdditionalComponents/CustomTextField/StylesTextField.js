import {TextField, withStyles} from "@material-ui/core";

const StylesTextField = withStyles({
  root: {
    minWidth: "211px",
    '& label.Mui-focused': {
      color: "#4d1919",
    },
    '& .MuiOutlinedInput-root': {
      '&.Mui-focused fieldset': {
        borderColor: "#4d1919",
      },
    },
  }
})(TextField);

export default StylesTextField