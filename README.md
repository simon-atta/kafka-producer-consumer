# Kafka Producer Consumer

## This service will publish and consumer messages from kafka.

There is endpoint will be used to publish and consumption will happen based on listener.

This API has two profiles:

1. SSL : This will be used to connect to secured KAFKA cluster
2. Default profile: will be used to connect to non-secured KAFKA cluster

### Prerequisites:

You should have kafka cluster up and running. You spain up instance based on docker based on this file: https://github.com/simon-atta/docker-compose/tree/master/kafka

### Tech Stack:
1. Java 
2. Spring boot