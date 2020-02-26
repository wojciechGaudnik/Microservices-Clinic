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
    setFunction,
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
            if (setFunction && specialFunction){
                setFunction(responseJSONData);
                specialFunction(responseJSONData);
            }else if (setFunction){
                setFunction(responseJSONData);
            }else if (specialFunction){
                specialFunction(responseJSONData);
            }
        })
};

export const getInfo = (userUUID, {setUserInformation}) => {
    let URL = 'http://localhost:8762/doctor-mssc/doctors/' + userUUID;
    const fetchData = fetch(URL, {
        method: 'GET',
        async: false,
        headers: {
            'Authorization': localStorage.token,
        }
    });
    fetchData
        .then(response => {
            return response.json();
        })
        .then(results => {
            console.log(results);
            setUserInformation(results);
        });
};

export const registerNewUser = (registerDetails) => {
    const URL = "http://localhost:8762/auth/users/";
    const userDetails = {
        "email": registerDetails.email,
        "password": registerDetails.password,
        "role": registerDetails.role
    };
    console.log(registerDetails);
    fetch(URL, {
        method: 'POST',
        async: false,
        body: JSON.stringify(userDetails),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then((response) => response.json())
        .then((responseData) => {
            console.log(responseData);
            registerNewDoctor(registerDetails, responseData.uuid, responseData.token);
        });
};

export const registerNewDoctor = (userDetails, uuid, token) => {
    const URL = "http://localhost:8762/doctor-mssc/doctors/";
    const registerDetails = {
        doctoruuid: uuid,
        firstName: userDetails.firstName,
        lastName: userDetails.lastName,
        photoUrl: userDetails.photoURL,
        licence: userDetails.licence
    };
    fetch(URL, {
        method: 'POST',
        async: false,
        body: JSON.stringify(registerDetails),
        headers: {
            'Authorization': token,
            'Content-Type': 'application/json;charset=UTF-8',
        }
    })
        .then((response) => {
            console.log(response);
            return response.json()
        })
        .then((responseData) => {
            console.log(responseData);
        });
};
