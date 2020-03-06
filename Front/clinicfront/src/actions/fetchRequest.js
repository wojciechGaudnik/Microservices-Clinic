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
            if (response.ok){
                ifCatchSetErrorInStore(false);
                return response.json()
            } else {
                ifCatchSetErrorInStore(true);
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