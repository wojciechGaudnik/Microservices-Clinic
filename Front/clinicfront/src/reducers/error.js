const error = (state = {isError: false, responseStatus: null}, action) => {
    switch (action.type) {
        case 'SET_ERROR':
            return {
                isError: action.error.isError,
                responseStatus: action.error.responseStatus
            };
        default:
            return state;
    }
};

export default error