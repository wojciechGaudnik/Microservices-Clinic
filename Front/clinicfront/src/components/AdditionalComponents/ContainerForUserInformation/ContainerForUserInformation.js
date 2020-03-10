import React, {useEffect, useState} from "react";
import {
    styleForContainer
} from "./Containers/SetContainerForUserInfo";
import {Badge, Container, Row, Spinner} from "react-bootstrap";
import {List, ListItemText, Typography} from "@material-ui/core";

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

    const displayEachInfo = (info, title) => {
        let value;
        switch (title) {
            case "Calendars":
                value = displayCalendars();
                break;
            case "Specializations":
                value = displaySpecializations();
                break;
            default:
                value = info;

        }
        return (
            <ListItemText
                primary={
                    <React.Fragment>
                        <Typography
                            component="span"
                            variant="overline"
                            color="textPrimary"
                        >
                            {title}
                        </Typography>
                    </React.Fragment>
                }
                secondary={
                    <React.Fragment>
                        <Typography
                            component="span"
                            variant="h6"
                            color="primary"
                        >
                            {info ? (value) : (<Spinner animation="border" size="sm" />)}
                        </Typography>
                    </React.Fragment>
                }
            />
        )
    };

    return(
        <Container style={styleForContainer}>
            <List>
                <Row><h5><Badge variant="primary">{props.titleRole}</Badge></h5></Row>
                {props.firstName        ? (displayEachInfo(userInformation.firstName, "First Name")) : null}
                {props.lastName         ? (displayEachInfo(userInformation.lastName, "Last Name")) : null}
                {props.licence          ? (displayEachInfo(userInformation.licence, "Licence")) : null}
                {props.calendars        ? (displayEachInfo(userInformation.calendars, "Calendars")) : null}
                {props.specializations  ? (displayEachInfo(userInformation.specializations, "Specializations")) : null}
                {props.medicalUnits     ? (displayEachInfo(userInformation.medicalUnits, "Medical Units")) : null}
                {props.pesel            ? (displayEachInfo(userInformation.pesel, "PESEL")) : null}
            </List>
        </Container>

    )
};