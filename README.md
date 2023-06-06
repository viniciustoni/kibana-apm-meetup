# Kibana APM - Meetup

Project used as example for a meetup presentation about Kibana APM.

It contains 2 small and simple services in order to show the basics functionalities from Kibana APM.

Together with the service this project contains the docker-compose files that you can use to start the elasticseach stack and apm server at your local for tests. 

# Stack

- Maven 3
- Spring-boot 3.1.0

# How to test it

- Clone the project into your workspace
- mvn clean verify

You will see that on your target/Generated-sources a folder called contracts and some generated tests code that is checking you API contract

# Link for the presentation

https://www.youtube.com/watch?v=pyA6-tVg2yI
