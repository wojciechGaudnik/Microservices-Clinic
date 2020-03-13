import React, {useEffect, useState} from "react";
import {Fade, Snackbar} from "@material-ui/core";
import MuiAlert from '@material-ui/lab/Alert';

export default function AlertMessage(props) {
    const { show, onClose, message, type } = props;

    function Alert(props) {
        return <MuiAlert elevation={6} variant="filled" {...props} />;
    }

    return (
        <Snackbar
            open={show}
            onClose={() => {onClose()}}
            autoHideDuration={3000}
            anchorOrigin={{vertical: 'top', horizontal: 'center'}}
        >
            <Alert
                onClose={() => {onClose()}}
                severity={type}
            >
                {message}
            </Alert>
        </Snackbar>
    );
}