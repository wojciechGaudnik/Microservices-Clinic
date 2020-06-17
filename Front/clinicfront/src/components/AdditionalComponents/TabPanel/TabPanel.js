import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import React from "react";

const styleForTypography = {
    border: "2.5px solid #4d1919",
    borderBottomRightRadius: "10px",
    borderBottomLeftRadius: "10px",
    backgroundColor: "white",
    opacity: "0.9",
};

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