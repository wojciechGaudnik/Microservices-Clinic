import React, {useCallback, useEffect, useState} from "react";

import {Button, Container, Tab, Tabs} from "@material-ui/core";

import {
    sendFetchRequestChangeUserInformation,
    sendFetchRequestDeleteUser,
    sendFetchRequestSetUserInformation,
    styleForBackToLoginPageButton,
    styleForMainDiv,
    styleForMainDivError
} from "./SetDoctorPage";
import {LogOutButton} from "../../AdditionalComponents/LogOutBtn/LogOutButton";
import {redirectByRole} from "../../../actions";
import DoctorInfoComponent from "./DoctorPageComponents/DoctorInfoComponent";
import EditDataFormComponent from "./DoctorPageComponents/EditDataFormComponent";
import DeleteAccountComponent from "./DoctorPageComponents/DeleteAccountComponent";
import AppBar from "@material-ui/core/AppBar";
import TabPanel from "../../AdditionalComponents/TabPanel";
import VisitsComponent from "./DoctorPageComponents/VisitsComponent";
import {styleForMainContainer} from "../../MainComponents/MainPage/SetMainPage";


export const DoctorPage = (props) => {
    const [showFormForEdit, setShowFormForEdit] = useState(false);
    const [userInformationHasBeenEdit, setUserInformationHasBeenEdit] = useState(false);
    const [value, setValue] = useState(0);

    const {
        userDetails,
        userInformation,
        setStoreUserInformation,
        setStoreError,
        error,
    } = props;

    useEffect(() => {setUserInformationHasBeenEdit(false)}, [userInformation]);

    //Fetch requests
    const fetchRequestForContainerForUserInformation = useCallback((setUserInformation)    => sendFetchRequestSetUserInformation(
        userDetails.uuid,
        setUserInformation,
        setStoreUserInformation,
        {ifCatchSetErrorInStore: (error) => {setStoreError(error)}},
        ), [userDetails.uuid, setStoreUserInformation, setStoreError]);

    const fetchRequestForDelAccountBtn               = ()                        => sendFetchRequestDeleteUser(
        userDetails.uuid);

    const fetchRequestForFormForInputUserInformation = (inputNewUserInformation) => sendFetchRequestChangeUserInformation(
        inputNewUserInformation,
        {ifCatchSetErrorInStore: (error) => {setStoreError(error)}},
        userDetails.uuid);

    //BackToLoginPageButton
    const onClickBackToLoginPageButton = () => {
        setStoreError({isError: false, response: null});
        redirectByRole(null, props);
    };

    const onClickChangeTabPanel = (event, newValue) => {
        setValue(newValue);
    };

    //Main HTML return
    return(<div>
        {error.isError ? (
            <div style={styleForMainDivError}>
                <Button
                    style={styleForBackToLoginPageButton}
                    variant="outlined"
                    size="large"
                    onClick={() => onClickBackToLoginPageButton()}
                >
                    Back to login site
                </Button>
            </div>
            ) : (
            <div style={styleForMainDiv}>
                <div>
                    <LogOutButton {...props}/>
                </div>
                <div>
                    <Container maxWidth="md" style={styleForMainContainer}>
                        <AppBar position="static">
                            <Tabs value={value} onChange={onClickChangeTabPanel} variant="fullWidth">
                                <Tab label="Visits"/>
                                <Tab label="User Information"/>
                                <Tab label="Edit User Information"/>
                                <Tab label="Delete Account"/>
                            </Tabs>
                        </AppBar>
                        <TabPanel value={value} index={0}>
                            <VisitsComponent/>
                        </TabPanel>
                        <TabPanel value={value} index={1}>
                            <DoctorInfoComponent
                                fetchRequest={fetchRequestForContainerForUserInformation}
                                userInformationHasBeenEdit={userInformationHasBeenEdit}
                            />
                        </TabPanel>
                        <TabPanel value={value} index={2}>
                            <EditDataFormComponent
                                fetchRequest={fetchRequestForFormForInputUserInformation}
                                submitButtonAdditionalActions={() => setUserInformationHasBeenEdit(true)}
                                {...props}
                            />
                        </TabPanel>
                        <TabPanel value={value} index={3}>
                            <DeleteAccountComponent
                                {...props}
                                fetchRequest={fetchRequestForDelAccountBtn}
                            />
                        </TabPanel>
                    </Container>
                </div>
            </div>
        )}
        </div>
    )
};

export default DoctorPage