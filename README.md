# Device-notes
Backend Developer Onboarding Assessment
------------------------------------------------

## Part A – Architecture Warm-up (Ready Answers)

### 1. Responsibilities of Controller, Service, Repository

Controller Layer
---------------->
Handles HTTP request/response mapping  
Extracts params, headers, body  
Delegates to service layer  
No business logic  

Service Layer
------------->
Contains business logic & validations  
Orchestrates multiple operations  
Ensures rules like note length, header validation  
Handles transactions if needed  

Repository Layer
---------------->
Direct DB interaction using JPA  
Query creation, sorting, filtering  
No business logic, only persistence operations  

------------------------------------------------

### 2. What is a database transaction?

A transaction is a group of DB operations that execute as a single unit (ACID properties).  
If one step fails, everything rolls back to maintain consistency.

Use `@Transactional` when:
- Multiple DB operations must succeed/fail together  
- Updating multiple tables  
- Ensuring data consistency  

Avoid `@Transactional` when:
- Only read operations  
- Single simple insert  
- Controller layer (should not be there)  

------------------------------------------------

### 3. Why DTOs instead of returning JPA entities?

DTOs (Data Transfer Objects) are used because:

- Prevent exposing internal DB structure  
- Avoid lazy loading / infinite JSON recursion issues  
- Control API response fields  
- Provide versioning flexibility  
- Improve security (hide sensitive fields)  

------------------------------------------------

### 4. What problem Liquibase solves?

Liquibase manages database schema versioning.

In multi-developer or multi-environment setup it:

- Ensures all environments use same schema version  
- Tracks DB changes using changelog  
- Prevents “works on my machine” DB issues  
- Supports rollback and audit history  
- Enables safe, controlled DB migrations  

------------------------------------------------

## Part B – Core Implementation (Device Notes Feature)

### Database (Liquibase)
Table: `device_note`

Columns:
- id (BIGINT, primary key, auto generated)
- device_id (BIGINT, not null)
- note (TEXT, max 1000 chars enforced at API level)
- created_at (TIMESTAMP)
- created_by (VARCHAR 100, not null)

Note:
Device table is not present, so foreign key is not added (as per requirement).

------------------------------------------------

## API Endpoints

### 1. Create Note

POST /api/v1/devices/{deviceId}/notes

Headers:
- Content-Type: application/json
- X-User: Mohan (mandatory)

Request Body:
{
  "note": "Device rebooted during maintenance"
}

Validations:
- note must not be blank
- note max length = 1000
- X-User header mandatory

Success Response:
{
  "id": 1,
  "deviceId": 1,
  "note": "Device rebooted during maintenance",
  "createdAt": "2026-02-17T23:05:10",
  "createdBy": "Mohan"
}

------------------------------------------------

### 2. List Notes

GET /api/v1/devices/{deviceId}/notes?limit=20

Rules:
- default limit = 20
- allowed range = 1 to 100
- sorted by created_at DESC

Response Example:
[
  {
    "id": 1,
    "deviceId": 1,
    "note": "Device rebooted during maintenance",
    "createdAt": "2026-02-17T23:05:10",
    "createdBy": "Mohan"
  }
]

------------------------------------------------

## Error Handling

Missing X-User header:
{
  "error": "Missing X-User header"
}

Blank note:
{
  "error": "Note cannot be blank"
}

Invalid limit:
{
  "error": "Invalid limit"
}

------------------------------------------------

## Logging Strategy

Controller (INFO)
- Logs incoming request with deviceId and username

Service (INFO)
- Logs successful note creation (noteId, deviceId)

WARN
- Logs validation failures or missing headers

------------------------------------------------

## How to Run Project

1. Clone project
2. Open in IntelliJ / STS
3. Run:

mvn clean install  
mvn spring-boot:run  

Application runs on:
http://localhost:9000

H2 Console:
http://localhost:9000/h2-console

JDBC URL:
jdbc:h2:mem:testdb

Username: sa  
Password: (empty)

------------------------------------------------

## Tech Stack

- Java 8
- Spring Boot 2.7
- Spring Data JPA
- Liquibase
- H2 Database
- Maven

------------------------------------------------

## Author

Mohan Singh Pawar
Java Backend Developer (3+ Years Experience)

------------------------------------------------
