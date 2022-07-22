# Recipes Backend

This project is spring boot project exposing REST API for clients to manage their recipes and ingredients
# Requirements
* JDK 11 
* Maven 

# How To Run locally 

* There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.mk.backend.assignement.receipes.ReceipesApplication class from your IDE.

* Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:
```shell
mvn spring-boot:run
```
* You can use docker :
1. run command : docker build -t recipes:latest .
2. run command :docker run -p 8080:8080 recipes:latest

# Open API documentation
* Once th application running you can access to REST API documentation via this link :http://localhost:8080/swagger-ui/index.html#/
