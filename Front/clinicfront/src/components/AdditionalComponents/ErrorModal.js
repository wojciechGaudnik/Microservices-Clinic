import React, {useState} from "react";
import {Container, Modal} from "react-bootstrap";

export const ErrorModal = () => {
    const [showErrorModal, setShowErrorModal] = useState(true);

    const styleForModalContainer = {
        background: 'red',
        borderRadius: '7px',
        border: '2px solid black'
    };

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