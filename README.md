# Microservices-Clinic
Implementation of medical unit administration system based on Spring Framework. The system is set up as distributed microservices architecture with use of Spring Boot tools. Main functionality of that system is registration of users(patients, doctors), managing doctors calendars and registering patients for visits.   

## Table of contents
* [Structure](#structure)
* [Technologies](#technologies)
* [Setup](#setup)
* [Screenshots](#screenshots)
* [Status](#status)
* [Contact](#contact)

## Structure
### Eureka
Service registry server.
### Zuul
API gateway.
### Authentication
Service responsible for authentication and authorization of users.
### Patient
Service implementing patient related logic.
### Doctor
Service implementing doctor related logic.
### Clinic
Service implementing clinic related logic.

## Technologies
* Java
* Spring
* Netflix microservices libs
* Hibernate
* PostgreSQL
* React

## Screenshots
![Example screenshot](./screenshots/mainPage.png)
![Example screenshot](./screenshots/filter.png)
![Example screenshot](./screenshots/cart.png)
![Example screenshot](./screenshots/checkout.png)
![Example screenshot](./screenshots/payment.png)
![Example screenshot](./screenshots/payPal.png)
![Example screenshot](./screenshots/creditCard.png)

## Status
Project is in progress.

## Contact
Created by [Weronika Walczak](mailto:weronikawalczak989@gmail.com) - feel free to contact me!
