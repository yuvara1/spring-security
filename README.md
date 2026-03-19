# Spring Boot JWT Security Example

This project demonstrates a simple implementation of JWT-based authentication and authorization using Spring Boot, Spring Security, and PostgreSQL.

## Features
- User registration and login with JWT token generation
- Stateless authentication using JWT
- Password hashing with BCrypt
- Custom user entity and repository
- Secure endpoints with role-based access
- Example endpoints for demonstration

## Technologies Used
- Java 21
- Spring Boot 4.x
- Spring Security
- Spring Data JPA
- PostgreSQL
- jjwt (JSON Web Token library)
- Lombok

## Getting Started

### Prerequisites
- Java 21+
- Maven
- PostgreSQL (running and accessible)

### Configuration
Edit `src/main/resources/application.properties` to match your PostgreSQL setup:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
jwt.secret=YOUR_SECRET_KEY
```

### Build and Run

1. Build the project:
   ```
mvn clean install
   ```
2. Run the application:
   ```
mvn spring-boot:run
   ```

### API Endpoints

#### Authentication
- `POST /api/auth/register` — Register a new user. Returns JWT token.
- `POST /api/auth/login` — Login with username/email and password. Returns JWT token.

#### User Endpoints (require JWT)
- `GET /api/users/get/{id}` — Example endpoint (dummy data)
- `GET /api/users/all-users` — Example endpoint (dummy data)
- `GET /api/users` — Example endpoint (dummy data)

### Example Request: Register
```
POST /api/auth/register
Content-Type: application/json
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123"
}
```

### Example Request: Login
```
POST /api/auth/login
Content-Type: application/json
{
  "usernameOrEmail": "testuser",
  "password": "password123"
}
```

### Using the JWT Token
Include the JWT token in the `Authorization` header for protected endpoints:
```
Authorization: Bearer <token>
```

## Project Structure
- `controller/` — REST controllers for authentication and user endpoints
- `entity/` — JPA entity for users
- `repository/` — Spring Data JPA repository
- `service/` — Business logic for user management
- `security/` — JWT utilities, authentication filter, and user details service
- `dto/request/` — DTOs for login and registration
- `config/` — Spring Security configuration

## Notes
- The JWT secret key should be long and random for production use.
- Passwords are stored securely using BCrypt.
- The project uses Lombok for boilerplate code reduction.
- The database schema is auto-generated/updated by Hibernate (see `spring.jpa.hibernate.ddl-auto`).

## License
This project is for educational/demo purposes.

