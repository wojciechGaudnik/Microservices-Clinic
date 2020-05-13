import React from "react";
import {styleForContainer} from "./SetContainerForUserInfo";
import {Badge, Container, Row} from "react-bootstrap";
import {List, ListItemText, Typography,} from "@material-ui/core";

export const ContainerForUserInformation = (props) => {

  const {
    doctorInformation,
    firstName,
    lastName,
    licence,
    specializations,
    pesel,
    titleRole
  } = props;

  //Main HTML return
  const displaySpecializations = () => {
    let specializationsShow = '';
    for (let item in doctorInformation.specializations) {
      if (doctorInformation.specializations.hasOwnProperty(item)) {
        specializationsShow += doctorInformation.specializations[item].name + "  ";
      }
    }
    return specializationsShow;
  };

  const displayEachInfo = (info, title) => {
    let value;
    if (title === "Specializations") {
      value = displaySpecializations();
    } else {
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

  return (
    <Container style={styleForContainer}>
      <List>
        <Row><h5><Badge variant="primary">{titleRole}</Badge></h5></Row>
        {firstName ? (displayEachInfo(doctorInformation.firstName, "First Name")) : null}
        {lastName ? (displayEachInfo(doctorInformation.lastName, "Last Name")) : null}
        {licence ? (displayEachInfo(doctorInformation.licence, "Licence")) : null}
        {specializations ? (displayEachInfo(doctorInformation.specializations, "Specializations")) : null}
        {pesel ? (displayEachInfo(doctorInformation.pesel, "PESEL")) : null}
      </List>
    </Container>

  )
};