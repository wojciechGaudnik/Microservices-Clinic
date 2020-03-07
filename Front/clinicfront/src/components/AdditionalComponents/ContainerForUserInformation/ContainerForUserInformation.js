import React, {useEffect, useState} from "react";
import {
    styleForContainer,
    styleForKeyCol,
    styleForRow,
    styleForValueCol
} from "./Containers/SetContainerForUserInfo";
import {Badge, Col, Container, Row} from "react-bootstrap";

export const ContainerForUserInformation = (props) => {
    const [userInformation, setUserInformation] = useState({});

    //Effects after each render
    useEffect(() => {
        props.fetchRequest(setUserInformation);
        props.setStoreUserInformation(userInformation);
    }, [userInformation]);

    //Main HTML return

    const displayCalendars = () => {
        let calendarsShow = '';
        for (let item in userInformation.calendars){
            if (userInformation.calendars.hasOwnProperty(item)){
                calendarsShow += userInformation.calendars[item].name + "  ";
            }
        }
        return calendarsShow;
    };

    const displaySpecializations = () => {
        let specializationsShow = '';
        for (let item in userInformation.specializations){
            if (userInformation.specializations.hasOwnProperty(item)) {
                specializationsShow += userInformation.specializations[item].name + "  ";
            }
        }
        return specializationsShow;
    };

    return(
        <Container style={styleForContainer}>
            <Row><h5><Badge variant="primary">{props.titleRole}</Badge></h5></Row>
            {props.firstName        ? (<Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>FirstName:</Col>      <Col style={styleForValueCol}>{userInformation.firstName}</Col>      </Row>) : null}
            {props.lastName         ? (<Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>LastName:</Col>       <Col style={styleForValueCol}>{userInformation.lastName}</Col>       </Row>) : null}
            {props.licence          ? (<Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Licence:</Col>        <Col style={styleForValueCol}>{userInformation.licence}</Col>        </Row>) : null}
            {props.calendars        ? (<Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Calendars:</Col>      <Col style={styleForValueCol}>{displayCalendars()}</Col>             </Row>) : null}
            {props.specializations  ? (<Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Specialization:</Col> <Col style={styleForValueCol}>{displaySpecializations()}</Col>       </Row>) : null}
            {props.medicalUnits     ? (<Row style={styleForRow}><Col xs={3} style={styleForKeyCol}>Medical Units:</Col>  <Col style={styleForValueCol}>{userInformation.medicalUnits}</Col>   </Row>) : null}
        </Container>
    )
};