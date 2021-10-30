# available-hotels-service

This is a project based on micro-service architecture which you can add & remove services easly without effecting current providers.

i'v used spring boot to build this project with as following:

  - Service Discovery (Eureka Server)
  - Rest Controllers
  
*********************************

# Please run services as following:
Build (Maven must be installed)
- mvn clean install

Run the app
- mvn spring-boot:run

Run the app in the following order: 
- Run  https://github.com/moaiad717/discovery-service.git   (must be first)
- Run  https://github.com/moaiad717/best-hotel-service.git
- Run  https://github.com/moaiad717/crazy-hotel-service.git
- Run  https://github.com/moaiad717/available-hotels-service.git

*********************************


Configure endpoints

navigate to yml config files "https://github.com/moaiad717/available-hotels-service/blob/master/src/main/resources/application_urls.properties" and configure your endpoints /
Services names.

Code is self documented, instruction comments added inside classes.
