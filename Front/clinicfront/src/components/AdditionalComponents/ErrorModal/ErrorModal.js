import React, {useState} from "react";

import {Container, Modal} from "react-bootstrap";

import {styleForModalContainer} from "./Containers/SetErrorModal";

export const ErrorModal = () => {
    const [showErrorModal, setShowErrorModal] = useState(true);

    return (
        <Modal show={showErrorModal} onHide={() => setShowErrorModal(false)} >
            <Container style={styleForModalContainer}>
                <Modal.Header closeButton>
                    <Modal.Title>Something goes wrong!</Modal.Title>
                </Modal.Header>
                <Modal.Body>Wrong login details</Modal.Body>
            </Container>
        </Modal>
    )
};