#!/bin/bash

# Wait for Kafka to start
sleep 10

# Create topic: patient-mail-topic
kafka-topics.sh --create \
  --bootstrap-server kafka:9092 \
  --replication-factor 1 \
  --partitions 3 \
  --topic patient-mail-topic \
  --if-not-exists
