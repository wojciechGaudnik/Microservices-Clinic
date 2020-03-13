import React, {useEffect, useState} from "react";
import {Fade, Snackbar} from "@material-ui/core";
import MuiAlert from '@material-ui/lab/Alert';

export default function AlertMessage(props) {
    const { error = null, setStoreError, message, type } = props;

    const [showWarningMessage, setShowWarningMessage] = useState(error["isError"]);

    useEffect(() => { setShowWarningMessage(error["isError"])}, [error["isError"]]);

    function Alert(props) {
        return <MuiAlert elevation={6} variant="filled" {...props} />;
    }

    return (
        <Snackbar
            open={showWarningMessage}
            onClose={() => {setStoreError(false)}}
            autoHideDuration={3000}
            TransitionComponent={Fade}
            anchorOrigin={{vertical: 'top', horizontal: 'center'}}
        >
            <Alert
                onClose={() => {setStoreError(false)}}
                severity={type}
            >
                {message}
            </Alert>
        </Snackbar>
    );
}