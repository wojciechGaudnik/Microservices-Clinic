const info = (state = {uuid: "empty", role:"empty"} , action) => {
    switch (action.type) {
        case 'SET_USER_DETAILS':
            return {userDetails: {
                uuid: action.userDetails.uuid,
                role: action.userDetails.role
            }};
        default:
            return state;
    }
};

export default info