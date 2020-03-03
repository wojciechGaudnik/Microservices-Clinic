import React, {useEffect, useState} from "react";
import {
    sendFetchRequestChangeUserInformation,
    sendFetchRequestDeleteUser,
    sendFetchRequestSetUserInformation
} from "../../containers/EmployeeContainers/SetPatientPage";

import {
    styleForMainDiv,
    styleForContainer,
    styleForRow,
    styleForValueCol,
    styleForKeyCol,
    styleForSubContainer
} from "../../containers/EmployeeContainers/SetEmployeePages"

import {Badge, Col, Container, Row} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import {FormForInputUserInformation} from "../FormForInputUserInformation";

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
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>UUID:</Col>           <Col style={styleForValueCol}>{props.userDetails.uuid}</Col>         </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Role:</Col>           <Col style={styleForValueCol}>{props.userDetails.role}</Col>         </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>FirstName:</Col>      <Col style={styleForValueCol}>{userInformation.firstName}</Col>      </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>LastName:</Col>       <Col style={styleForValueCol}>{userInformation.lastName}</Col>       </Row>
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