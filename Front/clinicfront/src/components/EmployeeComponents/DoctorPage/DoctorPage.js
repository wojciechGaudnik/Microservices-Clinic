import React, {useEffect, useState} from "react";

import {
    Button,
    Grid,
    Typography
} from "@material-ui/core";

import {
    sendFetchRequestChangeUserInformation,
    sendFetchRequestDeleteUser,
    sendFetchRequestSetUserInformation,
    styleForMainDiv,
    styleForMainGrid
} from "./SetDoctorPage";

import {FormForInputUserInformation} from "../../AdditionalComponents/FormForInputUseInfo/FormForInputUserInformation";
import {ErrorModal} from "../../AdditionalComponents/ErrorModal/ErrorModal";
import {LogOutButton} from "../../AdditionalComponents/LogOutBtn/LogOutButton";
import {ContainerForUserInformation} from "../../AdditionalComponents/ContainerForUserInformation/ContainerForUserInformation";
import {DelAccountBtn} from "../../AdditionalComponents/DelAccountBtn/DelAccountBtn";


export const DoctorPage = (props) => {
    const [showFormForEdit, setShowFormForEdit] = useState(false);
    const [userInformation, setUserInformation] = useState({});

    const {
        userDetails,
        setStoreUserInformation,
        setStoreError,
        error,
    } = props;

    //Effects after each render
    useEffect(() => {
        sendFetchRequestSetUserInformation(
            userDetails.uuid,
            setUserInformation,
            {ifCatchSetErrorInStore: (error) => {setStoreError(error)}}
            );
        if(!error.isError){setStoreUserInformation(userInformation)}
    }, [userInformation, setStoreUserInformation, userDetails.uuid]);

    useEffect(() => {console.log(error)}, [error]);

    //Fetch requests
    const fetchRequestForDelAccountBtn               = ()                        => sendFetchRequestDeleteUser(
        userDetails.uuid);

    const fetchRequestForFormForInputUserInformation = (inputNewUserInformation) => sendFetchRequestChangeUserInformation(
        inputNewUserInformation,
        {ifCatchSetErrorInStore: (error) => {setStoreError(error)}},
        userDetails.uuid);

    //Main HTML return
    return(
        <div style={styleForMainDiv}>
            {error.isError ? (<ErrorModal/>) : (
                <div>
                    <LogOutButton {...props}/>
                    <ContainerForUserInformation
                        {...props}
                        userInformation={userInformation}
                        titleRole={"DOCTOR"}
                        firstName={true}
                        lastName={true}
                        licence={true}
                        calendars={true}
                        specializations={true}
                        medicalUnits={true}
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
                        <Grid item>
                            <Typography
                                color="primary"
                                align="center"
                                variant="subtitle2"
                                gutterBottom={true}
                            >
                                Fill or change only variables which you want to change
                            </Typography>
                            <FormForInputUserInformation
                                {...props}
                                fetchRequest        ={fetchRequestForFormForInputUserInformation}
                                submitButtonTitle   ="Edit"
                                showEmailForm       ={true}
                                showPasswordForm    ={true}
                                showRoleForm        ={false}
                                showFirstNameForm   ={true}
                                showLastNameForm    ={true}
                                showLicenceForm     ={true}
                                showPhotoURLForm    ={true}
                            />
                        </Grid>) : null
                    }
                    <Grid item>
                        <DelAccountBtn
                        {...props}
                        fetchRequest={fetchRequestForDelAccountBtn}
                        />
                    </Grid>
                    </Grid>
                </div>
            )}
        </div>
    )
};

export default DoctorPage