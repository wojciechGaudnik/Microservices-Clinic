export const sendRequestByGivenDetails = (
    url,
    method,
    body,
    headers,
    setInStateFunction,
    specialFunction,
    ifCatchSetErrorInStore = () => {}
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
            if (response.ok){
                ifCatchSetErrorInStore({
                    isError: false,
                    responseStatus: response.status
                });
                console.log(response);
                return response.json()
            } else {
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
        .catch((err) => console.log(err))
};