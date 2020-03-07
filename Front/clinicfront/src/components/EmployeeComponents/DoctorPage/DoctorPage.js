import React, {useState} from "react";

import {Button, Container} from "react-bootstrap";

import {
    sendFetchRequestChangeUserInformation,
    sendFetchRequestDeleteUser,
    sendFetchRequestSetUserInformation,
    styleForMainDiv,
    styleForSubContainer
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
            <Container style={styleForSubContainer}>
                <Button variant="light" size="sm" block onClick={() => setShowFormForEdit(!showFormForEdit)}>
                    Edit
                </Button>
                <DelAccountBtn
                    {...props}
                    fetchRequest={fetchRequestForDelAccountBtn}
                />
            </Container>
            {showFormForEdit ? (
                <Container>
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
                </Container>) : null
            }
        </div>
    )
};

export default DoctorPage