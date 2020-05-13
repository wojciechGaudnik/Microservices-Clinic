import AppBar from "@material-ui/core/AppBar";
import Tabs from "@material-ui/core/Tabs";
import React, {useState} from "react";
import Tab from "@material-ui/core/Tab";
import {Container} from "@material-ui/core";
import {styleForMainContainer} from "./ContainerMainPage";
import TabPanel from "../../AdditionalComponents/TabPanel";
import LoginPage from "../LoginPage/LoginPage";
import ContainerLoginPage from "../LoginPage/ReduxContainerLoginPage";
import ContainerRegisterPage from "../RegisterPage/ContainerRegisterPage";
import RegisterPage from "../RegisterPage/RegisterPage";

export const MainPage = () => {
    const [whichPage, setPage] = useState(0);

    const handleChange = (event, newValue) => {
      setPage(newValue);
    };

    return (
        <Container maxWidth="sm" style={styleForMainContainer}>
            <AppBar position="static">
                <Tabs value={whichPage} onChange={handleChange} variant="fullWidth">
                    <Tab label="Log In"/>
                    <Tab label="Register"/>
                </Tabs>
            </AppBar>
            <TabPanel value={whichPage} index={0}>
              <ContainerLoginPage>
                {({userDetails, dispatchUserState, sendFetchForLoginUser}) => (
                  <LoginPage
                    userDetails={userDetails}
                    dispatchUserState={dispatchUserState}
                    sendFetchForLoginUser={sendFetchForLoginUser}
                  />
                )}
              </ContainerLoginPage>
            </TabPanel>
            <TabPanel value={whichPage} index={1}>
              <ContainerRegisterPage>
                {({fetchRegisterNewUser, registerStatus, dispatchRegisterStatus, handleChangeRole}) => (
                  <RegisterPage
                    fetchRegisterNewUser={fetchRegisterNewUser}
                    registerStatus={registerStatus}
                    dispatchRegisterStatus={dispatchRegisterStatus}
                    handleChangeRole={handleChangeRole}
                  />
                )}
              </ContainerRegisterPage>
            </TabPanel>
        </Container>
    );
};

export default MainPage;