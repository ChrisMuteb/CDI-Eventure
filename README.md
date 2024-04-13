# Eventure
An application that help you manage events. 

## Business layer
  Install:
    - java17
    - maven
    - navigate to: https://start.spring.io/

## Data layer
  Install:
    - postgre SQL 16 docker image
    - create the eventuredb database

## Docker 
  Commands:
    docker build -t eventure_server_img .
    docker run -d -p 8081:8081 --name=eventure_server_container eventure_server_img 

## CICD
  Steps:
    - create an ec2 instance running Jenkins
    - another ec2 that will host the actual application(ecs)
    - add a load balancer
    - add an APIgateway to proxy the backend

## Client layer
 Install:
   - Angular 17: HTML5, TailwindCSS, Typescript

## Future release
   - Mobile version of the app
