import {DelAccountBtn} from "../../../AdditionalComponents/DelAccountBtn/DelAccountBtn";
import React from "react";

export const DeleteAccountComponent = ({fetchRequest}) => {
  return(
    <DelAccountBtn
      fetchRequest={fetchRequest}
    />
  )
};

export default DeleteAccountComponent