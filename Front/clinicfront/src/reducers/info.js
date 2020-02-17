const info = (state = "Nothing", action) => {
    if (action.type === 'SET_MESSAGE') {
        return action.message;
    } else {
        return state;
    }
};

export default info