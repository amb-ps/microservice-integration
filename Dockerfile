FROM openjdk:8
EXPOSE 80
ADD target/microservice-integration microservice-integration.jar
ENTRYPOINT ["java","-jar","/microservice-integration.jar"]