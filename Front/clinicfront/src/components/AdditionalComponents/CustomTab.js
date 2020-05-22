import {withStyles} from "@material-ui/styles";
import {Tabs, Tab} from "@material-ui/core";
import React from "react";

export const CustomTabs = withStyles({
  root: {
    borderBottom: '1px solid #e8e8e8',
    backgroundColor: "#4d1919"
  },
  indicator: {
    backgroundColor: 'white',
  },
})(Tabs);

export const CustomTab = withStyles(() => ({
  root: {
    backgroundColor: "#4d1919",
    color: "white"
  },
  textColorInherit: {
    opacity: "0.7",
  }
}))((props) => <Tab disableRipple {...props} />);


