import React, {useEffect, useState} from "react";
import {getInfo, sendRequestByGivenDetails} from "../../actions";
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
        sendRequestByGivenDetails(
            'http://localhost:8762/doctor-mssc/doctors/' + props.userDetails.uuid,
            'GET',
            null,
            {'Authorization': localStorage.token},
            (responseData) => setUserInformation(responseData),
            false
        );
        // getInfo(props.userDetails.uuid, {setUserInformation});
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
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Calendars:</Col>      <Col style={styleForValueCol}>{displayCalendars()}</Col>      </Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Specialization:</Col> <Col style={styleForValueCol}>{displaySpecializations()}</Col></Row>
                <Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Medical Units:</Col>  <Col style={styleForValueCol}>{userInformation.medicalUnits}</Col>   </Row>
            </Container>
        </div>
    )
};

export default DoctorPage