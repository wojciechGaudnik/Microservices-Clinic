import React, {useState} from "react";

import {Button, Container} from "react-bootstrap";

import {
    sendFetchRequestChangeUserInformation,
    sendFetchRequestDeleteUser,
    sendFetchRequestSetUserInformation
} from "../../containers/EmployeeContainers/SetDoctorPage";

import {styleForMainDiv, styleForSubContainer} from "../../containers/EmployeeContainers/SetDoctorPage"

import {redirectByRole} from "../../actions";

import {FormForInputUserInformation} from "../AdditionalComponents/FormForInputUseInfo/FormForInputUserInformation";
import {ErrorModal} from "../AdditionalComponents/ErrorModal/ErrorModal";
import {LogOutButton} from "../AdditionalComponents/LogOutBtn/LogOutButton";
import {ContainerForUserInformation} from "../AdditionalComponents/ContainerForUserInformation/ContainerForUserInformation";
import {DelAccountBtn} from "../AdditionalComponents/DelAccountBtn/DelAccountBtn";


export const DoctorPage = (props) => {
    const [showFormForEdit, setShowFormForEdit] = useState(false);
    //Main HTML return

    return(
        <div style={styleForMainDiv}>
            {props.error ? (<ErrorModal/>) : null}
            <LogOutButton {...props}/>
            <ContainerForUserInformation
                {...props}
                fetchRequest={({setUserInformation}) => sendFetchRequestSetUserInformation(props.userDetails.uuid, {setUserInformation})}
                setStoreUserInformation={(userInformation) => props.setStoreUserInformation(userInformation)}
                titleRole={"DOCTOR"}
                firstName={true}
                lastName={true}
                licence={true}
                calendars={true}
                specializations={true}
                medicalUnits={true}
            />
            <Container style={styleForSubContainer}>
                <Button variant="light" size="sm" block onClick={() => setShowFormForEdit(!showFormForEdit)}>
                    Edit
                </Button>
                <DelAccountBtn {...props} fetchRequest={() => sendFetchRequestDeleteUser({uuid: props.userDetails.uuid})}/>
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