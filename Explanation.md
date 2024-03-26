# Project Startup Guide

## Starting Grafana

To start the Grafana server, follow these steps:
  - Navigate to the Grafana folder in your command prompt.
  - Run the 'docker-compose.yml' file located in the Grafana folder using the command " docker-compose up ".
  - Once the command is executed, access Grafana at http://localhost:3000/.
  - Log in to the server.
  - Set the datasource as Prometheus. Prometheus server runs on port 9090, accessible at http://localhost:9090/.


## Starting the Spring Boot Application

To start the Spring Boot application, follow these steps:

 - Ensure that Docker is running.
 - Start the Spring Boot application.
 - The Spring Actuator status will be visible in Prometheus at http://localhost:9090/targets?search=. 
 - Test all the REST API endpoints.

### Documentation with Swagger
 - The code is documented using Swagger API. 
 - Access Swagger at http://localhost:8080/swagger-ui/index.html#/ to test the REST API endpoints.
https://drive.google.com/file/d/1IZsbqIqduxLOGHG7ma_8hXGSk3h0fbiY/view?usp=drive_link
 - Test the RESTAPI end points ,

 - For Storing the data , the in memory H2 database was used
 

## Data in Grafana Dashboard

 - After testing the REST Api endpoints , the data will be reflected in the Grafana Dashboards.
 - Dashboards, Panels, Queries were already created 

### Metrics data in Grafana Dashboard
https://drive.google.com/file/d/1xxLrxsOQKXmVcIXLYXBrWa5TUC0j46IE/view?usp=drive_link

  - Request rate: Number of requests created.
  - Response time: Average response time of the requests created.
  - Error rate: Percentage of requests that resulted in errors created.
  - Custom metrics: Spring Data Jpa metrics for monitoring the Repository performance.
  - System load: CPU and memory utilization of the Spring Boot application were implemented

### Grafana json files

- json file for spring boot system monitor
https://drive.google.com/file/d/1V50-gL9rij7Mvbn-3o0sjj5P2Yx44hmX/view?usp=drive_link


- json file for Request rate, Response time, Error rate, Jpa Repository Average time
  https://drive.google.com/file/d/16HnAUvmmO0X0pIrleYwvxcvHs39-TeAk/view

- Ensuring that the Grafana JSON files are stored in the "grafana json files" folder within the "user-assignment" project.

## Code Quality 

 - The code has tested through the junit and mockito, the code is well-structured, readable, and maintainable

## Grafana Dashboard images 

 https://drive.google.com/drive/folders/1H6b6-uIxDygfRKQlveP1auZYu7PrHdSe?usp=drive_link
