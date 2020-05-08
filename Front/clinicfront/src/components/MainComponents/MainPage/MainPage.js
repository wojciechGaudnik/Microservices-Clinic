import AppBar from "@material-ui/core/AppBar";
import Tabs from "@material-ui/core/Tabs";
import React, {useState} from "react";
import Tab from "@material-ui/core/Tab";
import RegisterPage from "../RegisterPage/SetRegisterPage";
import {Container} from "@material-ui/core";
import {styleForMainContainer} from "./SetMainPage";
import TabPanel from "../../AdditionalComponents/TabPanel";
import LoginPage from "../LoginPage/LoginPage";
import ContainerLoginPage from "../LoginPage/ReduxContainerLoginPage";

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
              <ContainerLoginPage
                {...props}
              >
                {({userDetails, dispatchUserState, sendFetchForLoginUser}) => (
                  <>
                    <LoginPage
                      userDetails={userDetails}
                      dispatchUserState={dispatchUserState}
                      sendFetchForLoginUser={sendFetchForLoginUser}
                    />
                  </>
                )}
              </ContainerLoginPage>
            </TabPanel>
            <TabPanel value={value} index={1}>
                <RegisterPage {...props}/>
            </TabPanel>
        </Container>
    );
};