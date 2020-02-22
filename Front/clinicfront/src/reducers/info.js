const info = (state = {
    userDetails: {
        uuid: "empty",
        role: "empty"
    },
    userInformation: {
        doctorUUID: "empty",
        firstName: "empty",
        lastName: "empty",
        photoURL: "empty",
        licence: "empty",
        calendars: "empty",
        specializations: "empty",
        medicalUnits: "empty"
    }} , action) => {
    switch (action.type) {
        case 'SET_USER_DETAILS':
            return {
                userDetails: {
                    uuid: action.userDetails.uuid,
                    role: action.userDetails.role
                }, userInformation: state.userInformation
            };
        case 'SET_USER_INFORMATION':
            return {
                userDetails: state.userDetails,
                userInformation: {
                    doctoruuid: action.userInformation.doctoruuid,
                    firstName: action.userInformation.firstName,
                    lastName: action.userInformation.lastName,
                    photoURL: action.userInformation.photoURL,
                    licence: action.userInformation.licence,
                    calendars: action.userInformation.calendars,
                    specializations: action.userInformation.specializations,
                    medicalUnits: action.userInformation.medicalUnits
                }
            };
        default:
            return state;
    }
};

export default info