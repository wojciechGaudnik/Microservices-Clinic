import React, {useCallback, useEffect, useState} from "react";
import {Badge, Button, Container, Row} from "react-bootstrap";

import {
    sendFetchRequestChangeUserInformation,
    sendFetchRequestDeleteUser,
    sendFetchRequestSetUserInformation,
    styleForBackToLoginPageButton,
    styleForMainContainer,
    styleForMainDiv,
    styleForMainDivError,
    styleForSubContainer
} from "./SetPatientPage";

import {styleForContainer} from "../../AdditionalComponents/ContainerForUserInformation/SetContainerForUserInfo"

import {FormForInputUserInformation} from "../../AdditionalComponents/FormForInputUserInfo/FormForInputUserInformation";
import {LogOutButton} from "../../AdditionalComponents/LogOutBtn/LogOutButton";
import AppBar from "@material-ui/core/AppBar";
import {Tab, Tabs} from "@material-ui/core";
import TabPanel from "../../AdditionalComponents/TabPanel";
import VisitsComponent from "../DoctorPage/DoctorPageComponents/VisitsComponent";
import DeleteAccountComponent from "../DoctorPage/DoctorPageComponents/DeleteAccountComponent";
import {redirectByRole} from "../../../actions";
import PatientInfoComponent from "./PatientPageComponents/PatientInfoComponent";
import EditDataFormComponent from "./PatientPageComponents/EditDataFormComponent";

export const PatientPage = (props) => {
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

    return(
        <div>
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
                                <VisitsComponent
                                    doctorUUID={userDetails.uuid}
                                />
                            </TabPanel>
                            <TabPanel value={value} index={1}>
                                <PatientInfoComponent
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

export default PatientPage