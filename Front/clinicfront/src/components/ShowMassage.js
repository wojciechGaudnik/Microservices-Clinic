import React from "react";
import Login from "../containers/Login";

const ShowMassage = ({ result }) => (
    <div>
        <p>{result}</p>
        <Login />
    </div>
);

export default ShowMassage