# Kibana APM - Meetup

Project used as example for a meetup presentation about Kibana APM.

It contains 2 small and simple services in order to show the basics functionalities from Kibana APM.

Together with the service this project contains the docker-compose files that you can use to start the elasticseach stack and apm server at your local for tests. 

# Stack

- Maven 3
- Spring-boot 3.1.0
- Kibana APM
- Postgres
- Docker compose

# How to test it

- Clone the project into your workspace
- mvn clean verify
- Start the docker images present on docker/docker-compose.yaml `docker compose up`
- Configure the APM into your kibana, you can access the kibana-ui via: http://localhost:5601/
- Start the sales-products app, swagger UI: http://localhost:8081/api/swagger-ui/index.html
- Start the sales-order app, swagger UI: http://localhost:8082/api/swagger-ui/index.html

# Java agent:

In case you want to use the APM agent via java agent flag, please add this configuration for the VM Options.
```
-javaagent:$PROJECT_HOME\kibana-apm-meetup\elastic-apm-agent-1.39.0.jar 
-Delastic.apm.server_url=http://localhost:8200 
-Delastic.apm.service_name=sales-product 
-Delastic.apm.application_packages=com.vagai 
-Delastic.apm.environment=meetup-kibana-apm 
-Delastic.apm.trace_methods=com.vagai.*
-Delastic.apm.span_min_duration=10ms
```