import React, {useEffect, useState} from "react";
import {Badge, Button, Container, Row} from "react-bootstrap";

import {
    sendFetchRequestChangeUserInformation,
    sendFetchRequestDeleteUser,
    sendFetchRequestSetUserInformation,
    styleForMainDiv,
    styleForSubContainer
} from "./SetPatientPage";

import {styleForContainer} from "../../AdditionalComponents/ContainerForUserInformation/Containers/SetContainerForUserInfo"

import {FormForInputUserInformation} from "../../AdditionalComponents/FormForInputUseInfo/FormForInputUserInformation";

export const PatientPage = (props) => {
    const [showFormForEdit, setShowFormForEdit] = useState(false);
    const [userInformation, setUserInformation] = useState({
        patientUUID:     "",
        firstName:      "",
        lastName:       ""
    });

    //Effects after each render
    useEffect(() => {
        sendFetchRequestSetUserInformation(props.userDetails.uuid, {setUserInformation});
        props.setStoreUserInformation(userInformation);
    }, [userInformation.patientUUID]);

    //Main HTML return
    return(
        <div style={styleForMainDiv}>
            <Container style={styleForContainer}>
                <Row><h5><Badge variant="primary">PATIENT</Badge></h5></Row>
            </Container>
            <Container style={styleForSubContainer}>
                <Button variant="light" size="sm" block onClick={() => setShowFormForEdit(!showFormForEdit)}>
                    Edit
                </Button>
                <Button variant="light" size="sm" block onClick={() => sendFetchRequestDeleteUser()}>
                    Delete Account
                </Button>
            </Container>
            {showFormForEdit ? (
                <Container>
                    <FormForInputUserInformation {...props} sendFetchRequest={sendFetchRequestChangeUserInformation} variant="edit"/>
                </Container>
                ) : null
            }
        </div>
    )
};

export default PatientPage