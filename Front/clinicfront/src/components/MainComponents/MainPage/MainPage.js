import AppBar from "@material-ui/core/AppBar";
import Tabs from "@material-ui/core/Tabs";
import React, {useState} from "react";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import LoginPage from "../LoginPage/SetLoginPage";
import RegisterPage from "../RegisterPage/SetRegisterPage";
import {Container} from "@material-ui/core";
import {styleForMainContainer, styleForTypography} from "./SetMainPage";

function TabPanel(props) {
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
}

export const MainPage = (props) => {
    const [value, setValue] = useState(0);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    return (
        <Container maxWidth="sm" style={styleForMainContainer}>
            <AppBar position="static">
                <Tabs value={value} onChange={handleChange} variant="fullWidth">
                    <Tab label="Log In"/>
                    <Tab label="Register"/>
                </Tabs>
            </AppBar>
            <TabPanel value={value} index={0}>
                <LoginPage {...props}/>
            </TabPanel>
            <TabPanel value={value} index={1}>
                <RegisterPage {...props}/>
            </TabPanel>
        </Container>
    );
};