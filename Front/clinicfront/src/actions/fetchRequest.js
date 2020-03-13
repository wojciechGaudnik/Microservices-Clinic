export const sendRequestByGivenDetails = (
    url,
    method,
    body,
    headers,
    setInStateFunction,
    specialFunction,
    ifCatchSetErrorInStore = (error) => {}
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
        .then((response) => {
            console.log(response);
            if (response.ok){
                ifCatchSetErrorInStore({
                    isError: false,
                    responseStatus: response.status
                });
                return response.json()
            } else {
                console.log("Error");
                ifCatchSetErrorInStore({
                    isError: true,
                    responseStatus: response.status
                });
            }
        })
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
        .catch(err => console.log(err.message))
};