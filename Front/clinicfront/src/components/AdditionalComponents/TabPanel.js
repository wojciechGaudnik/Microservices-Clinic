import Typography from "@material-ui/core/Typography";
import {styleForTypography} from "../MainComponents/MainPage/ContainerMainPage";
import Box from "@material-ui/core/Box";
import React from "react";

export const TabPanel = (props) => {
    const { children, value, index} = props;

    return (
        <Typography
            component="div"
            role="tabpanel"
            hidden={value !== index}
            style={styleForTypography}
        >
            {value === index && <Box p={3}>{children}</Box>}
        </Typography>
    );
};

export default TabPanel