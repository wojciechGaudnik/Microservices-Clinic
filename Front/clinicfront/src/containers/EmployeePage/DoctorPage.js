import React, {useEffect, useState} from "react";
import {getInfo} from "../../actions";
import {Badge, Row, Container, Col} from "react-bootstrap";

export const DoctorPage = (props) => {
    const [userInformation, setUserInformation] = useState({
        doctorUUID: "empty",
        firstName: "empty",
        lastName: "empty",
        photoURL: "empty",
        licence: "empty",
        calendars: "empty",
        specializations: "empty",
        medicalUnits: "empty"
    });

    //Effects after each render
    useEffect(() => {
        getInfo(props.userDetails.uuid, {setUserInformation});
        props.setStoreUserInformation(userInformation);
    }, [userInformation.doctorUUID]);

    //CSS stylesheet
    const styleForKeyCol = {
        textAlign: 'right',
        border: '2px solid grey',
        borderRadius: '5px',
        background: '#cce6ff'
    };

    const styleForValueCol = {
        marginLeft: '3px',
        border: '2px solid grey',
        borderRadius: '5px',
        background: '#cce6ff'
    };

    const styleForRow = {
        marginBottom: '5px'
    };

    const styleForContainer = {
        color: 'black',
        padding: '10px 30px 10px 30px',
        width: '50%',
        border: '2px solid white',
        borderRadius: '7px',

    };

    const styleForMainDiv = {
        margin: '30px'
    };

    //Main HTML return
    return(
        <div style={styleForMainDiv}>
            <Container style={styleForContainer}>
                <Row><h5><Badge variant="primary">DOCTOR</Badge></h5></Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>UUID:</Col>           <Col style={styleForValueCol}>{props.userDetails.uuid}</Col>         </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Role:</Col>           <Col style={styleForValueCol}>{props.userDetails.role}</Col>         </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>FirstName:</Col>      <Col style={styleForValueCol}>{userInformation.firstName}</Col>      </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>LastName:</Col>       <Col style={styleForValueCol}>{userInformation.lastName}</Col>       </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Licence:</Col>        <Col style={styleForValueCol}>{userInformation.licence}</Col>        </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Calendars:</Col>      <Col style={styleForValueCol}>{userInformation.calendars}</Col>      </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Specialization:</Col> <Col style={styleForValueCol}>{userInformation.specializations}</Col></Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Medical Units:</Col>  <Col style={styleForValueCol}>{userInformation.medicalUnits}</Col>   </Row>
            </Container>
        </div>
    )
};

export default DoctorPage