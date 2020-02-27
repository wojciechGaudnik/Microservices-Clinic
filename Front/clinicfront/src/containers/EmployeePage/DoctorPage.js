import React, {useEffect, useState} from "react";
import {Badge, Col, Container, Row} from "react-bootstrap";

import {
    sendFetchRequestSetUserInformation,
    styleForMainDiv, styleForContainer, styleForRow, styleForValueCol, styleForKeyCol
} from "./EmployeeContainers/SetDoctorPage";


export const DoctorPage = (props) => {
    const [userInformation, setUserInformation] = useState({
        doctorUUID:     "",
        firstName:      "",
        lastName:       "",
        photoURL:       "",
        licence:        "",
        calendars:      "",
        specializations:"",
        medicalUnits:   ""
    });

    //Effects after each render
    useEffect(() => {
        sendFetchRequestSetUserInformation(props.userDetails.uuid, {setUserInformation});
        props.setStoreUserInformation(userInformation);
    }, [userInformation.doctorUUID]);

    //Main HTML return

    const displayCalendars = () => {
        let calendarsShow = '';
        for (let item in userInformation.calendars){
            calendarsShow += userInformation.calendars[item].name + "  ";
        }
        return calendarsShow;
    };

    const displaySpecializations = () => {
        let specializationsShow = '';
        for (let item in userInformation.specializations){
            specializationsShow += userInformation.specializations[item].name + "  ";
        }
        return specializationsShow;
    };

    return(
        <div style={styleForMainDiv}>
            <Container style={styleForContainer}>
                <Row><h5><Badge variant="primary">DOCTOR</Badge></h5></Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>UUID:</Col>           <Col style={styleForValueCol}>{props.userDetails.uuid}</Col>         </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Role:</Col>           <Col style={styleForValueCol}>{props.userDetails.role}</Col>         </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>FirstName:</Col>      <Col style={styleForValueCol}>{userInformation.firstName}</Col>      </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>LastName:</Col>       <Col style={styleForValueCol}>{userInformation.lastName}</Col>       </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Licence:</Col>        <Col style={styleForValueCol}>{userInformation.licence}</Col>        </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Calendars:</Col>      <Col style={styleForValueCol}>{displayCalendars()}</Col>             </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Specialization:</Col> <Col style={styleForValueCol}>{displaySpecializations()}</Col>       </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Medical Units:</Col>  <Col style={styleForValueCol}>{userInformation.medicalUnits}</Col>   </Row>
            </Container>
        </div>
    )
};

export default DoctorPage