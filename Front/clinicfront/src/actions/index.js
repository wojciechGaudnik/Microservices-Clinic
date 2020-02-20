export const setMessage = message => ({
   type: 'SET_MESSAGE',
   message
});

//USEFUL FUNCTIONS

export const redirectTo = (site, props) => {
    props.history.push(site)
};

//TODO: Create function to get Data by fetch(URL) two below functions to one

export const checkUserByLocalToken = ({setUserDetails}) => {
   const fetchData = fetch('http://localhost:8762/auth/login', {
      method: 'GET',
      async: false,
      headers: {
         'Authorization': localStorage.token,
      }
   });

   fetchData
       .then(response => {
          return response.json();
       })
       .then(results => {
           setUserDetails(results.uuid)
       });
};

export const getInfo = ({setUserDetails}, userUUID) => {
    const fetchData = fetch('http://localhost:8762/doctor-mssc/doctors/' + userUUID, {
        method: 'GET',
        async: false,
        headers: {
            'Authorization': localStorage.token,
        }
    });

    fetchData
        .then(response => {
            console.log(response);
            return response.json();
        })
        .then(results => {
            console.log(results)
            // setUserDetails({
            //
            // })
        });
};
