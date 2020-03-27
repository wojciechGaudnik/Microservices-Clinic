import React, {useCallback, useEffect, useState} from "react";

import {Button, Grid} from "@material-ui/core";

import {
    sendFetchRequestChangeUserInformation,
    sendFetchRequestDeleteUser,
    sendFetchRequestSetUserInformation,
    styleForBackToLoginPageButton,
    styleForMainDiv,
    styleForMainDivError,
    styleForMainGrid
} from "./SetDoctorPage";
import {LogOutButton} from "../../AdditionalComponents/LogOutBtn/LogOutButton";
import {redirectByRole} from "../../../actions";
import DoctorInfoComponent from "./DoctorPageComponents/DoctorInfoComponent";
import EditDataFormComponent from "./DoctorPageComponents/EditDataFormComponent";
import DeleteAccountComponent from "./DoctorPageComponents/DeleteAccountComponent";


export const DoctorPage = (props) => {
    const [showFormForEdit, setShowFormForEdit] = useState(false);
    const [userInformationHasBeenEdit, setUserInformationHasBeenEdit] = useState(false);

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
                    <DoctorInfoComponent
                        fetchRequest={fetchRequestForContainerForUserInformation}
                        userInformationHasBeenEdit={userInformationHasBeenEdit}
                    />
                    <Grid
                        style={styleForMainGrid}
                        container
                        direction="column"
                        justify="space-around"
                        alignItems="stretch"
                        spacing={3}
                    >
                    <Grid item>
                        <Button variant="contained" color="primary" disableElevation fullWidth onClick={() => setShowFormForEdit(!showFormForEdit)}>
                            Edit
                        </Button>
                    </Grid>
                    {showFormForEdit ? (
                        <EditDataFormComponent
                            fetchRequest={fetchRequestForFormForInputUserInformation}
                            submitButtonAdditionalActions={() => setUserInformationHasBeenEdit(true)}
                            {...props}
                        />) : null
                    }
                    <Grid item>
                        <DeleteAccountComponent
                            {...props}
                            fetchRequest={fetchRequestForDelAccountBtn}
                        />
                    </Grid>
                    </Grid>
                </div>
            </div>
        )}
        </div>
    )
};

export default DoctorPage