import React, {useEffect, useState} from "react";
import {styleForContainer} from "./SetContainerForUserInfo";
import {Badge, Container, Row} from "react-bootstrap";
import {List, ListItemText, Typography,} from "@material-ui/core";

export const ContainerForUserInformation = (props) => {

    const {
        userInformationHasBeenEdit,
        fetchRequest,
        firstName,
        lastName,
        licence,
        calendars,
        specializations,
        medicalUnits,
        pesel,
        titleRole
    } = props;

    const [userInformation, setUserInformation] = useState({});

    //Effects after each render
    //Here we have loop
    useEffect(() => {
        fetchRequest(setUserInformation);
    }, [fetchRequest]);

    //Here we have loop
    useEffect(() => {
        if (userInformationHasBeenEdit){fetchRequest(setUserInformation);}
    }, [userInformationHasBeenEdit, fetchRequest]);


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
                            {info ? (value) : null}
                        </Typography>
                    </React.Fragment>
                }
            />
        )
    };

    return(
        <Container style={styleForContainer}>
            <List>
                <Row><h5><Badge variant="primary">{titleRole}</Badge></h5></Row>
                {firstName        ? (displayEachInfo(userInformation.firstName, "First Name")) : null}
                {lastName         ? (displayEachInfo(userInformation.lastName, "Last Name")) : null}
                {licence          ? (displayEachInfo(userInformation.licence, "Licence")) : null}
                {calendars        ? (displayEachInfo(userInformation.calendars, "Calendars")) : null}
                {specializations  ? (displayEachInfo(userInformation.specializations, "Specializations")) : null}
                {medicalUnits     ? (displayEachInfo(userInformation.medicalUnits, "Medical Units")) : null}
                {pesel            ? (displayEachInfo(userInformation.pesel, "PESEL")) : null}
            </List>
        </Container>

    )
};