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



Code is self documented, instruction comments added inside classes.
*********************************

# Rsults from PostMan : 
![image](https://user-images.githubusercontent.com/37189526/139557881-a995fbaa-36b6-4fa7-a659-24d5a0a9debb.png)
