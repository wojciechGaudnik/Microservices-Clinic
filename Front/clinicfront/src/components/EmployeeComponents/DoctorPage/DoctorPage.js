import React, {useState} from "react";

import {Button, Grid} from "@material-ui/core";
import {Container} from "react-bootstrap";

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

    //Fetch requests
    const fetchRequestForContainerForUserInformation = (setUserInformation)    => sendFetchRequestSetUserInformation(
        props.userDetails.uuid,
        setUserInformation);

    const fetchRequestForDelAccountBtn               = ()                        => sendFetchRequestDeleteUser(
        props.userDetails.uuid);

    const fetchRequestForFormForInputUserInformation = (inputNewUserInformation) => sendFetchRequestChangeUserInformation(
        inputNewUserInformation,
        {ifCatchSetErrorInStore: (error) => {props.setStoreError(error)}},
        props.userDetails.uuid);

    //Main HTML return
    return(
        <div style={styleForMainDiv}>
            {props.error ? (<ErrorModal/>) : null}
            <LogOutButton {...props}/>
            <ContainerForUserInformation
                {...props}
                fetchRequest={fetchRequestForContainerForUserInformation}
                setStoreUserInformation={(userInformation) => props.setStoreUserInformation(userInformation)}
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
                        <Button variant="contained" color="primary" disableElevation block fullWidth onClick={() => setShowFormForEdit(!showFormForEdit)}>
                            Edit
                        </Button>
                    </Grid>
                    <Grid item>
                        <DelAccountBtn
                            {...props}
                            fetchRequest={fetchRequestForDelAccountBtn}
                        />
                    </Grid>
                        {showFormForEdit ? (
                            <Grid item>
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
                </Grid>
        </div>
    )
};

export default DoctorPage