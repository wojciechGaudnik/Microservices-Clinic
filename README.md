# Microservices-Clinic
Implementation of medical unit administration system based on Spring Framework. The system is set up as distributed microservices architecture with use of Spring Boot tools. Main functionality of that system is registration of users(patients, doctors), managing doctors calendars and registering patients for visits.   

## Table of contents
* [Architecture ](#architecture)
* [Tech_Stack](#tech_stack)
* [Screenshots](#screenshots)
* [TODO](#TODO)
* [Contact](#contact)

# Architecture 
![Example screenshot](./Screenshots/Clinics%20Portal%20MSSC%20Architecture.png)
### Eureka
Service registry server.
### Zuul
API gateway responsible for authorization.
### Authentication
Service responsible for authentication.
### Patient
Service implementing patient related logic.
### Doctor
Service implementing doctor related logic.
### Clinic
Service implementing clinic related logic.
### Security
![Example screenshot](./Screenshots/Registration_Log%20in_Pull%20out_%20%20Data%20Flow.png)
### Example communication between mssc 
![Example screenshot](./Screenshots/Visit%20registration.png)
# Tech_Stack 
* Java 13
* Spring Boot 2 
* Netflix Eureka
* DB Postgres
* JPA / Hibernate 
* Swagger 2
* React
# Screenshots
![Example screenshot](./Screenshots/Login.png)
![Example screenshot](./Screenshots/Edit.png)
![Example screenshot](./Screenshots/Information.png)
![Example screenshot](./Screenshots/Visits.png)
![Example screenshot](./Screenshots/Delete.png)

## TODO in progress
* doctor's unitests tests and integration tests <--- move here tests from postman 
* search (kafka || rabit && elasticSearch)
* statistic mssc in .NET MVC
* config mssc 
* rewrite to use faign, hateos  

Project is in progress.

## Contact
Created by:
* [Gaudnik Wojciech](mailto:gaudnik.wojciech@gmail.com) - feel free to contact me! 
* Adamowski Wojciech
* Walczak Weronika
