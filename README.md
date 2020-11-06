# Minesweeper API
A simple backend to provide services to the minesweeper app.

## Technologies
- Java 11
- Spark
- Guice
- JUnit5
- H2 Database

## Project Structure
The project architecture is based on 'Clean Architecture' and is divided in four main layers:
- Core: This layer contains the business logic, and It's completely isolated from libraries implementation. 
Also this layer is divided in three packages: 
    - Entity: Contains all the needed classes that have the data to be processed in use cases. 
    - Repository: Contains interfaces that communicate the use cases with external 
    resources in order to abstract from the specific implementations regarding the resource type.
    - Usecase: Here is the logic of the game.
- Configuration: In this layer you will find the configurations and utility classes.
- Entrypoint: This layer acts as a facade through which clients will interact with the use cases.
- Repository: This layer has the implementations that allow use cases to interact with external resources.

## Environment
The application is deployed in Heroku.
- Health check: https://hojeda-minesweeper-api.herokuapp.com/ping

To run the application locally you can use the next command:
`./gradlew build && java -jar build/libs/*shadow.jar`

The project includes a test coverage verification plugin and each build runs the verification, 
currently the instruction coverage rate is up to 96%.

## API Documentation
SwaggerHub: https://app.swaggerhub.com/apis/herojeda/minesweeper/1.0.0#/

## API Client
Kotlin client: https://github.com/herojeda/minesweeper-klient