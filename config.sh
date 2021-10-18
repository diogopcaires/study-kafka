#!bin/bash

#command to start zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

#command to start kafka
bin/kafka-server-start.sh config/server.properties

#command to start a consumer for a topic in cmd
#bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic {TOPIC_NAME] --from-beginning

#command to start a producer for a topic in cmd
#bin/kafka-console-producer.sh --broker-list localhost:9092 --topic {TOPIC_NAME]

#command to create a topic
# bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic {TOPIC_NAME}

#command to describe the topics in a cluster
#bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe

#config for changing number of partitions inside a topic
bin/kafka-topics.sh --alter --bootstrap-server localhost:9092 --topic STORE_NEW_ORDER --partitions 3

