const info = (state = {message: "Nothing"} , action) => {
    switch (action.type) {
        case 'SET_MESSAGE':
            return {message: action.message};
        default:
            return state;
    }
};

export default info