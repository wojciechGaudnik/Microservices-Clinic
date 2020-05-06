import {Container} from "@material-ui/core";
import React, {useEffect, useState} from "react";
import {
    sendFetchRequestSetAllDoctors,
    WhichDoctorForm
} from "./ContainersForDoctorPageComponents/ContainerForVisitRegistration";

export const VisitRegistration = () => {
    /*TODO: Pobieranie wszystkich doktorów i wybranie jednego z nich:
        1. Stworzenie żądania do pobierania wszystkich doktorów
        2. Utworzyć status dla wszystkich doktorów | (tutaj mamy już wszystkich doktorów i ich informacje)
        3. Stworzenie wywołania żadania po pierwszym wywołaniu komponentu i zapisania wyniku w statusie
        4. Stworzyć listę wszystkich doktorów do wyboru | (czyli select dla wielu) | {Imię + Nazwisko}
                                      !!!SPRAWDZAMY KAŻDY KROK!!!
    */
    const [allDoctors, setAllDoctors] = useState(null);
    const [selectedDoctor, setSelectedDoctor] = useState(null);

    const handleChangeForSelectedDoctor = (event) => {
        setSelectedDoctor(event.target.value)
    };

    useEffect(() => {
        sendFetchRequestSetAllDoctors();
    }, []);

    return(
        <Container maxWidth="lg">
            <p>Wizyty</p>
            <WhichDoctorForm handleChange={handleChangeForSelectedDoctor} whichDoctor={selectedDoctor} allDoctors={allDoctors}/>
        </Container>
    );
};

export default VisitRegistration