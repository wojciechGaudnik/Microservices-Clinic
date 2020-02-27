export const setStoreUserDetails = userDetails => ({
    type: 'SET_USER_DETAILS',
    userDetails
});

export const setStoreUserInformation = userInformation => ({
    type: 'SET_USER_INFORMATION',
    userInformation
});

//USEFUL FUNCTIONS

export const redirectByRole = (role, props) => {
    switch (role) {
        case "doctor":
            props.history.push("/doctor");
            return;
        case "assistant":
            props.history.push("/assistant");
            return;
        case "patient":
            props.history.push("/patient");
            return;
        default:
            return;
    }
};

export const sendRequestByGivenDetails = (
    url,
    method,
    body,
    headers,
    setInStateFunction,
    specialFunction,
) => {
    let init = {
        method: method,
        async: false,
        headers: headers,
    };

    if (body){
        init.body = JSON.stringify(body)
    }

    fetch(url, init)
        .then((response) => response.json())
        .then((responseJSONData) => {
            if (setInStateFunction && specialFunction){
                setInStateFunction(responseJSONData);
                specialFunction(responseJSONData);
            }else if (setInStateFunction){
                setInStateFunction(responseJSONData);
            }else if (specialFunction){
                specialFunction(responseJSONData);
            }
        })
};
