import AppBar from "@material-ui/core/AppBar";
import React, {useState} from "react";
import {Container} from "@material-ui/core";
import TabPanel from "../../AdditionalComponents/TabPanel";
import LoginPage from "../LoginPage/LoginPage";
import ContainerLoginPage from "../LoginPage/ReduxContainerLoginPage";
import ContainerRegisterPage from "../RegisterPage/ContainerRegisterPage";
import RegisterPage from "../RegisterPage/RegisterPage";
import {CustomTab, CustomTabs} from "../../AdditionalComponents/CustomTab";
import TabCustomTypography from "../../AdditionalComponents/CustomTypography/TabCustomTypography";

//CSS Stylesheet
const styleForMainContainer = {
  marginTop: "50px"
};

const styleForTabPanel = {
  backgroundColor: "white",
};

export const MainPage = () => {
    const [whichPage, setPage] = useState(0);

    const handleChange = (event, newValue) => {
      setPage(newValue);
    };

    return (
        <Container maxWidth="sm" style={styleForMainContainer}>
            <AppBar position="static">
                <CustomTabs
                  value={whichPage}
                  onChange={handleChange}
                  variant="fullWidth"
                >
                    <CustomTab
                      label={
                        <TabCustomTypography
                          primaryLabel={"Logowanie"}
                          secondaryLabel={"Log In"}
                        />
                      }
                    />
                    <CustomTab
                      label={
                        <TabCustomTypography
                          primaryLabel={"Rejestracja"}
                          secondaryLabel={"Register"}
                        />
                      }
                    />
                </CustomTabs>
            </AppBar>
            <TabPanel
              value={whichPage}
              index={0}
              classes={styleForTabPanel}
            >
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
            <TabPanel
              value={whichPage}
              index={1}
              classes={styleForTabPanel}
            >
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