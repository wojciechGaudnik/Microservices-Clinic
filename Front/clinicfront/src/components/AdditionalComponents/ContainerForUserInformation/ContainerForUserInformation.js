import React from "react";
import {styleForContainer} from "./SetContainerForUserInfo";
import {Badge, Container, Row} from "react-bootstrap";
import {List, ListItemText, Typography,} from "@material-ui/core";

export const ContainerForUserInformation = (props) => {

  const {
    userInformation,
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
    for (let item in userInformation.specializations) {
      if (userInformation.specializations.hasOwnProperty(item)) {
        specializationsShow += userInformation.specializations[item].name + "  ";
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
              style={{color: "#4d1919"}}
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
        <Row><h5><Badge variant="primary" style={{backgroundColor: "#4d1919"}}>{titleRole}</Badge></h5></Row>
        {firstName ? (displayEachInfo(userInformation.firstName, "First Name")) : null}
        {lastName ? (displayEachInfo(userInformation.lastName, "Last Name")) : null}
        {licence ? (displayEachInfo(userInformation.licence, "Licence")) : null}
        {specializations ? (displayEachInfo(userInformation.specializations, "Specializations")) : null}
        {pesel ? (displayEachInfo(userInformation.pesel, "PESEL")) : null}
      </List>
    </Container>

  )
};