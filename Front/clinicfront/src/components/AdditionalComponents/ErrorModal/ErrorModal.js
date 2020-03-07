import React, {useState} from "react";

import {Container, Modal} from "react-bootstrap";

import {styleForModalContainer} from "./Containers/SetErrorModal";

export const ErrorModal = (props) => {
    const [showErrorModal, setShowErrorModal] = useState(true);

    const onHideClick = () => setShowErrorModal(false);

    return (
        <Modal show={showErrorModal} onHide={onHideClick()} >
            <Container style={styleForModalContainer}>
                <Modal.Header closeButton>
                    <Modal.Title> {props.modalTitle} </Modal.Title>
                </Modal.Header>
            </Container>
        </Modal>
    )
};