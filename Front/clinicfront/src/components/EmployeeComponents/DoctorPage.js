import React, {useEffect, useState} from "react";
import {Badge, Col, Container, Row, Button} from "react-bootstrap";

import {
    sendFetchRequestChangeUserInformation,
    sendFetchRequestDeleteUser,
    sendFetchRequestSetUserInformation
} from "../../containers/EmployeeContainers/SetDoctorPage";

import { 
    styleForMainDiv,
    styleForContainer,
    styleForRow,
    styleForValueCol,
    styleForKeyCol,
    styleForSubContainer
} from "../../containers/EmployeeContainers/SetEmployeePages"

import {FormForInputUserInformation} from "../AdditionalComponents/FormForInputUseInfo/FormForInputUserInformation";
import {ErrorModal} from "../AdditionalComponents/ErrorModal/ErrorModal";
import {LogOutButton} from "../AdditionalComponents/LogOutBtn/LogOutButton";
import {redirectByRole} from "../../actions";

export const DoctorPage = (props) => {
    const [showFormForEdit, setShowFormForEdit] = useState(false);
    const [userInformation, setUserInformation] = useState({
        doctorUUID:     "",
        firstName:      "",
        lastName:       "",
        photoUrl:       "",
        licence:        "",
        calendars:      "",
        specializations:"",
        medicalUnits:   ""
    });

    //Effects after each render
    useEffect(() => {
        sendFetchRequestSetUserInformation(props.userDetails.uuid, {setUserInformation});
        props.setStoreUserInformation(userInformation);
        console.log(userInformation.photoUrl)
    }, [userInformation.doctorUUID]);

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
        <div style={styleForMainDiv}>
            {props.error ? (<ErrorModal/>) : null}
            <LogOutButton {...props}/>
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
            <Container style={styleForSubContainer}>
                <Button variant="light" size="sm" block onClick={() => setShowFormForEdit(!showFormForEdit)}>
                    Edit
                </Button>
                <Button variant="light" size="sm" block onClick={() => {
                    sendFetchRequestDeleteUser({uuid: props.userDetails.uuid});
                    localStorage.removeItem("token");
                    redirectByRole(null, props);
                }}>
                    Delete Account
                </Button>
            </Container>
            {showFormForEdit ? (
                <Container>
                    <FormForInputUserInformation
                        {...props}
                        fetchRequest        ={(newUserInformation) => {
                            sendFetchRequestChangeUserInformation(
                                newUserInformation,
                                {ifCatchSetErrorInStore: (error) => {props.setStoreError(error)}},
                                {uuid: props.userDetails.uuid})
                        }}
                        submitButtonTitle   ="Edit"
                        showEmailForm       ={true}
                        showPasswordForm    ={true}
                        showRoleForm        ={false}
                        showFirstNameForm   ={true}
                        showLastNameForm    ={true}
                        showLicenceForm     ={true}
                        showPhotoURLForm    ={true}
                    />
                </Container>) : null
            }
        </div>
    )
};

export default DoctorPage