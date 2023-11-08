# nortviz
Demo project for User Management

## Description
This application provides a set APIs to perform the following;
 * Create user
 * Get user by id
 * Get user by username
 * Save image
 * Get images belonged to a particular user
 * Get image by id
 * Delete image by id

 
The application uses Imgur APIs to save images and delete them on request

A default admin user will be created at the time of start of application. This admin user can be used to create other users.
Credentials for the admin - username: admin, password: admin

## Requirements
 * JDK 11
 * Maven 3

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.nortvis.demo.DemoApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```


