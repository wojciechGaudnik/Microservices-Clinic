import React, {useState} from "react";
import {Modal} from "react-bootstrap";

export const ErrorModal = () => {
    const [showErrorModal, setShowErrorModal] = useState(true);

    return (
        <Modal show={showErrorModal} onHide={() => setShowErrorModal(false)}>
            <Modal.Header closeButton>
                <Modal.Title>Something goes wrong!</Modal.Title>
            </Modal.Header>
            <Modal.Body>Wrong login details</Modal.Body>
        </Modal>
    )
};