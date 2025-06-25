🚑 QuickMedic - Hospital Management Microservice
QuickMedic is a hospital management microservice built with Spring Boot, designed for clean REST APIs, role-based security, and scalable architecture.

🚀 Features
✅ JWT Authentication + Role-Based Access (USER / ADMIN)
✅ Admin Capabilities

Grant admin role to users

View individual / all user details

Delete users (with role cleanup)

✅ User Capabilities

Secure registration (BCrypt password encoding)

JWT-based login

💻 Tech Stack




⚙️ Architecture
pgsql
Copy
Edit
Spring Boot App (UserAuth Service)
│
├── Spring Security + JWT
├── MySQL (User + Role tables)
└── REST APIs for user/admin
👉 Planned:

Redis caching

Kafka event streams

Docker deployment

📂 Endpoints
Endpoint	Method	Access	Description
/public/register	POST	Public	Register new user
/public/login	POST	Public	Login & get JWT
/admin/grant-admin/id/{id}	POST	ADMIN	Grant admin rights
/admin/get-user-details/id/{id}	GET	ADMIN	Fetch user info
/admin/get-users	GET	ADMIN	List all users
/admin/delete-user-by-Id/{id}	DELETE	ADMIN	Delete user + roles

🛠 Run Locally
bash
Copy
Edit
git clone https://github.com/aryan735/QuickClinic
cd QuickClinic
mvn clean install
mvn spring-boot:run
✅ Configure your application.properties for MySQL connection!

🔮 Planned Enhancements
Redis caching

Kafka for audit/event streaming

Docker & cloud deployment

API rate limiting

Swagger UI docs

🙌 Author
Aryan Raj
📞 8294299735
📧 aryanraj.dev.net@gmail.com
🔗 LinkedIn

