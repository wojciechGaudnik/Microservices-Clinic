import React, {useEffect, useState} from "react";
import { checkLoginUser } from "../actions/CheckLoginUser";

const TestFETCHComponent = () => {
    const [state, setState] = useState("false");

    useEffect(() => checkLoginUser({setState}));

    return  (<div>
                <p>{state}</p>
                <button onClick={}>LogOut</button>
            </div>);
};

export default TestFETCHComponent;