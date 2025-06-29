
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

---

## 🙌 Author

**Aryan Raj**
📧 [aryanraj.dev.net@gmail.com](mailto:aryanraj.dev.net@gmail.com)
🔗 [LinkedIn](https://www.linkedin.com/in/aryan-raj-2b9598326/)
🔗 [X](https://x.com/aryann_dev)
