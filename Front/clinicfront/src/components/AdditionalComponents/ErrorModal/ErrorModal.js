import React, {useState} from "react";

import {Container, Modal} from "react-bootstrap";

import {styleForModalContainer} from "./Containers/SetErrorModal";

export const ErrorModal = (props) => {
    const [showErrorModal, setShowErrorModal] = useState(true);

    return (
        <Modal show={showErrorModal} onHide={() => setShowErrorModal(false)} >
            <Container style={styleForModalContainer}>
                <Modal.Header closeButton> <Modal.Title>{props.modalTitle}</Modal.Title> </Modal.Header>
                <Modal.Body>{props.modalMessage}</Modal.Body>
            </Container>
        </Modal>
    )
};