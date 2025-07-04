
# 🚑 QuickMedic - Hospital Management Microservices

**QuickMedic** is a scalable microservices-based hospital management system built using **Spring Boot**, designed with clean REST APIs, role-based security, modular architecture, and now includes service discovery and API gateway integration.

---

## 🚀 Features

### ✅ Authentication & Authorization

* JWT-based authentication
* Role-based access control (`USER`, `ADMIN`)

### ✅ Admin Capabilities

* Grant admin role to users
* View all users or fetch by ID
* Delete users and clean associated roles

### ✅ User Capabilities

* Secure registration (BCrypt password encoding)
* Login to receive JWT tokens

---

## ⚙️ Microservices Architecture

| Microservice                | Description                                      |
| --------------------------- | ------------------------------------------------ |
| **Service Registry**        | Built with Eureka Server for service discovery   |
| **API Gateway**             | Central entry point using Spring Cloud Gateway   |
| **UserAuth Service**        | Handles login, registration, and role-based auth |
| *(Planned)* Patient Service | Will handle patient records & appointment mgmt   |

---

## 💻 Tech Stack

* Java 17
* Spring Boot 3.0.5
* Spring Cloud 2022.0.5
* Spring Security + JWT
* Eureka Server & Client
* Spring Cloud Gateway
* MySQL (user data)

### 👉 Planned Additions:

* Redis (for caching)
* Kafka (for event streaming)
* Docker (for containerization & deployment)

---

## 📂 Key API Endpoints

| Endpoint                          | Method | Access | Description                 |
| --------------------------------- | ------ | ------ | --------------------------- |
| `/public/register`                | POST   | Public | Register a new user         |
| `/public/login`                   | POST   | Public | Login & receive JWT token   |
| `/admin/grant-admin/id/{id}`      | POST   | ADMIN  | Grant admin privileges      |
| `/admin/get-user-details/id/{id}` | GET    | ADMIN  | Fetch specific user details |
| `/admin/get-users`                | GET    | ADMIN  | Get all registered users    |
| `/admin/delete-user-by-Id/{id}`   | DELETE | ADMIN  | Delete user and roles       |

✅ All routes are now accessible through the **API Gateway** (`http://localhost:8085/...`)

---

## 🛠 Run Locally

```bash
git clone https://github.com/aryan735/QuickClinic
cd QuickClinic
mvn clean install
```

Start services in order:

1. Service Registry (Eureka)
2. UserAuth Service
3. API Gateway

✅ Configure `application.yml` / `application.properties` for MySQL and Eureka

---

## 📦 Git Hygiene

This project uses a `.gitignore` to exclude:

* Build artifacts (`target/`)
* IDE configs (`.idea/`, `*.iml`)
* Logs, OS temp files
* Environment secrets (`.env`)

---

## 🔮 Planned Enhancements

* ✅ Service Registry with Eureka
* ✅ Spring Cloud Gateway
* ☑️ Patient microservice
* ☑️ Redis caching
* ☑️ Kafka for audit/event streaming
* ☑️ Dockerized deployment
* ☑️ Swagger UI integration
* ☑️ API rate limiting & monitoring


**QuickClinic Patient Microservice & Kafka Setup Documentation**

---

### 🌐 Project Context

This documentation captures all the core changes and configurations made to the **Patient Microservice** in the `QuickClinic` project. It includes:

* Microservice setup and structure
* Kafka integration setup using Docker Compose
* JavaMailSender configuration
* Git commands and best practices for commit

---

## 📂 Directory Structure (Updated)

```
QuickClinic/
├── quickclinic-patient/
│   ├── src/main/java/com/quickclinic/patient/
│   │   ├── controller/
│   │   │   └── PatientController.java
│   │   ├── dtos/
│   │   │   ├── PatientRequestDto.java
│   │   │   ├── PatientResponseDto.java
│   │   │   └── PatientUpdateDto.java
│   │   ├── entity/PatientModel.java
│   │   ├── repository/PatientRespository.java
│   │   ├── service/PatientService.java
│   │   ├── exceptions/
│   │   │   ├── GlobalException.java
│   │   │   ├── ExceptionModel.java
│   │   │   └── PatientException.java
│   │   ├── client/UserClient.java
│   └── resources/application.yml
├── docker/
│   └── kafka-init.sh
├── docker-compose.yml
```

---

## 🚀 Kafka + JavaMailSender Setup

### `application.yml` (Patient Microservice)

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: patient-mail-group
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring:
          json:
            trusted:
              packages: com.quickclinic.patient.dtos
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer

  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-password
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```

### `docker-compose.yml`

```yaml
version: '3.8'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.4.0
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - "2181:2181"

  kafka:
    image: confluentinc/cp-kafka:7.4.0
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
    depends_on:
      - zookeeper
```

### `kafka-init.sh`

```bash
#!/bin/bash

sleep 10

kafka-topics.sh --create \
  --bootstrap-server kafka:9092 \
  --replication-factor 1 \
  --partitions 3 \
  --topic patient-mail-topic \
  --if-not-exists
```

> Ensure the script uses **LF line endings**, not CRLF.
> VS Code ➔ Bottom-right corner ➔ Set to `LF`

---

## 📃 Git Commands Used

```bash
# Stage docker folder & files
git add docker/
git add docker-compose.yml

# Optional: re-stage LF-safe script
git rm --cached docker/kafka-init.sh
git add docker/kafka-init.sh

# Commit message
git commit -m "chore(docker): added docker-compose setup and Kafka topic initializer script"

# Push to GitHub
git push origin main

# Later commit for config updates:
git add .
git commit -m "Added kafka config, database config, java mail sender config"
git push origin main
```

## 🔌 Kafka Integration (Infrastructure Setup)

We have integrated **self-hosted Kafka** into QuickClinic using Docker Compose to enable asynchronous communication between microservices.

### 📦 Technologies Used
- **Kafka** (via `confluentinc/cp-kafka:7.4.0`)
- **Zookeeper** (via `confluentinc/cp-zookeeper:7.4.0`)
- **Docker Compose**

### 📂 Location
Kafka and Zookeeper are defined in the `docker-compose.yml` file at the root of the project.

### ✅ Current Status
- Kafka & Zookeeper are fully set up and running using Docker.
- Docker Compose ensures Kafka runs alongside other services in both development and production environments.
- Topics can be managed via CLI (`kafka-topics`).

### 🔜 Upcoming Integration Plan
We will use Kafka for event-driven communication:
- `PatientService` will **publish** events (e.g., `patient.registered`)
- `NotificationService` will **consume** those events and send confirmation emails

Kafka will allow our services to remain decoupled, scalable, and more resilient.

---
### 🛰️ Kafka Integration (Self-Hosted)

- Set up **Kafka & Zookeeper** via Docker Compose for local development and production deployment.
- Patient microservice:
  - Publishes `PatientRegisteredEvent` to topic `patient.registered` upon new application.
- Notification service:
  - Listens to `patient.registered` topic using Kafka Consumer.
  - Sends a confirmation email with full application data to the patient.
- Fully logged with SLF4J for observability and debugging.


📝 **Last Updated:** July 2025


## 🙌 Author

**Aryan Raj**
📧 [aryanraj.dev.net@gmail.com](mailto:aryanraj.dev.net@gmail.com)
🔗 [LinkedIn](https://www.linkedin.com/in/aryan-raj-2b9598326/)
🔗 [X](https://x.com/aryann_dev)
