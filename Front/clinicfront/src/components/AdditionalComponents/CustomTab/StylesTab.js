import {withStyles} from "@material-ui/styles";
import {Tabs, Tab} from "@material-ui/core";
import React from "react";

export const StylesTabs = withStyles({
  root: {
    borderBottom: '1px solid #e8e8e8',
    backgroundColor: "#4d1919",
  },
  indicator: {
    backgroundColor: 'white',
  },
})(Tabs);

export const StylesTab = withStyles(() => ({
  root: {
    backgroundColor: "#4d1919",
    color: "white",
    width: "auto",
    maxWidth: "none",
    margin: "auto"
  },
  textColorInherit: {
    opacity: "0.7",
  }
}))((props) => <Tab disableRipple {...props} />);


