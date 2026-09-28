TaskFlow Learning Progress

Project

Project: TaskFlow

Direction: Java Backend + Docker

Repository: GitHub TaskFlow

Local path: D:\TaskFlow\taskflow

Java: JDK 21

Build tool: Maven

Main package: com.taskflow

Current branch: feature/spring-boot

Learning Roadmap

Java Foundation
→ Collections & Generics
→ Exceptions & Optional
→ Lambda & Stream API
→ Maven
→ SQL / MySQL
→ HTTP / REST
→ Spring Boot
→ JPA / Hibernate
→ DTO / Validation
→ Security / JWT
→ Testing
→ Redis
→ RabbitMQ
→ Docker
→ CI/CD
→ Advanced / Microservices / Cloud

Note: TaskFlow uses MySQL 8.4 in Docker, not PostgreSQL.

Current Progress

Java Foundation       ✅ Completed
Collections           ✅ Completed
Generics              ✅ Completed
Exceptions            ✅ Completed
Optional              ✅ Completed
Lambda                ✅ Completed
Stream API            ✅ Completed
Maven                 🔄 Used / continuing with Spring Boot
SQL / MySQL           🔄 Basic integration completed
HTTP / REST            🔄 CRUD API completed
Spring Boot            🔄 In Progress
JPA / Hibernate        🔄 In Progress
DTO / Validation       ⏳ Next
Security / JWT         ⏳ Later
Testing                ⏳ Later
Redis                  ⏳ Later
RabbitMQ               ⏳ Later
Docker                 🔄 MySQL already running with Docker
CI/CD                  ⏳ Later
Advanced / Cloud       ⏳ Later

Java Foundation

Variables / Data Types

Operators

If / Else

Validation

Methods

Loops

Arrays

Class & Object

Constructor

Encapsulation

Inheritance

Polymorphism

Abstraction

Interface

Collections

List

Set

Map

ArrayList

HashSet

HashMap

Searching Task by ID

Iterating through collections

Duplicate prevention

Map.Entry / entrySet()

Generics

Generic Type <T>

Generic Class Box<T>

Diamond Operator <>

Generic Method <T>

Bounded Generic <T extends Task>

Generic Repository

Bounded Generic Repository

Exceptions

try / catch / finally

throw / throws

Checked Exception

Unchecked Exception

Custom Exception

InvalidTaskProgressException

TaskNotFoundException

Exception handling practice

Exception handling in GenericRepository

findById() with custom exception

Optional

Status: ✅ Completed

Why null can be problematic

Optional<T>{=html}

Optional.of()

Optional.ofNullable()

Optional.empty()

isPresent()

orElse()

orElseGet()

orElseThrow()

Optional with Task

Optional in Repository

ifPresent()

map()

filter()

map() + filter()

orElseThrow() with Task

Lambda

Status: ✅ Completed

Lambda expression basic

Lambda with parameters

Lambda with block body

Lambda with if

Lambda with Task

@FunctionalInterface

Custom functional interface TaskAction

Consumer<T>{=html}

Predicate<T>{=html}

Function<T, R>

Lambda with List

Lambda with Task collection

Stream API

Status: ✅ Completed

stream()

forEach()

filter()

map()

sorted()

distinct()

limit()

filter() + sorted()

filter() + sorted() + limit()

filter() + map()

Stream + Lambda

Collectors.toList()

Collectors.toSet()

Collectors.toMap()

count()

filter() + count()

anyMatch()

allMatch()

noneMatch()

findFirst()

findFirst() + filter()

findFirst() + Optional

findFirst() + Optional.map()

findFirst() + Optional.orElse()

findAny()

findAny() + filter()

Spring Boot + JPA + REST

Status: 🔄 In Progress

Spring Boot Setup

Create Spring Boot project

Java 21

Maven

Spring Boot 4.1.0

Spring Web

Spring Data JPA

MySQL Connector

Validation dependency

Spring Boot application startup

Health endpoint

Docker + MySQL

Docker Desktop

Docker Compose

MySQL 8.4 container

Container: taskflow-mysql

Database: taskflow_db

MySQL connection from Spring Boot

Hibernate/JPA connection verification

Compose file:

docker/compose.yaml

JPA Entity

Create TaskEntity

@Entity

@Id

@GeneratedValue

Default constructor for JPA

Constructor for Task creation

Getters / Setters

Hibernate generated task_entity table

Repository

Create TaskRepository

Extend JpaRepository<TaskEntity, Long>

findAll()

save()

findById()

deleteById()

Service

Create TaskService

Constructor Injection

getAllTasks()

createTask()

getTaskById()

updateTask()

deleteTask()

Controller

Create TaskController

@RestController

@RequestMapping("/api/tasks")

GET /api/tasks

GET /api/tasks/{id}

POST /api/tasks

PUT /api/tasks/{id}

DELETE /api/tasks/{id}

REST API Verification

All basic CRUD operations were tested successfully with Postman:

POST   /api/tasks        ✅
GET    /api/tasks        ✅
GET    /api/tasks/{id}   ✅
PUT    /api/tasks/{id}   ✅
DELETE /api/tasks/{id}   ✅

The Task was created, read, updated, and deleted successfully through
the API and MySQL.

Important Project Files

src/main/java/com/taskflow/

├── Main.java
├── Task.java
├── FeatureTask.java
├── BugTask.java
├── ImprovementTask.java
├── Assignable.java
├── Box.java
├── GenericRepository.java
├── InvalidTaskProgressException.java
├── TaskNotFoundException.java
├── TaskAction.java
├── TaskflowApplication.java
│
├── controller/
│   ├── HealthController.java
│   └── TaskController.java
│
├── entity/
│   └── TaskEntity.java
│
├── repository/
│   └── TaskRepository.java
│
└── service/
    └── TaskService.java

Current Architecture

HTTP Client / Postman
        ↓
TaskController
        ↓
TaskService
        ↓
TaskRepository
        ↓
TaskEntity / JPA / Hibernate
        ↓
MySQL 8.4
        ↓
Docker

This is currently a layered modular monolith.

Advanced concepts such as Redis, RabbitMQ/Kafka, microservices, CI/CD,
and cloud deployment will be introduced later rather than copied
directly from the FinSight reference project.

Git Branch Strategy

main
→ stable

develop
→ development

feature/*
→ individual learning / feature branches

Current branch:

feature/spring-boot

Git Checkpoints

Important checkpoints:

Java Foundation
→ completed

Stream API
→ completed

Spring Boot + MySQL + JPA + basic REST
→ commit: 7e22e37
  feat: add spring boot mysql task api

Task Create + Find By ID
→ commit: ba08b34
  feat: add task create and find by id

Task CRUD
→ completed and committed
  (latest commit hash should be checked with `git log` when resuming)

Working tree at the end of today's session:

clean

Current Stop Point --- 2026-09-28

Today's session completed the first practical Spring Boot CRUD API.

Completed today:

Spring Boot project setup

MySQL 8.4 with Docker

Spring Boot ↔ MySQL connection

TaskEntity

TaskRepository

TaskService

TaskController

GET all tasks

POST create task

GET task by ID

PUT update task

DELETE task

Tested CRUD using Postman

Verified data was persisted and updated in MySQL

Deleted Task and verified it was gone

Git checkpoint committed

Working tree clean

Next Learning Session

Do not restart Java Foundation, Optional, Lambda, or Stream API.

Continue from:

Spring Boot + REST + JPA
        ↓
Improve CRUD API

Next logical steps:

Handle TaskNotFoundException / 404
        ↓
Validation
        ↓
DTO
        ↓
Global Exception Handling
        ↓
Improve REST responses
        ↓
Testing

The first next task should be:

GET /api/tasks/{id}
        ↓
Task does not exist
        ↓
404 Not Found

Do not jump directly to Security/JWT, Redis, RabbitMQ, or Microservices.

Standard Learning Flow

For every TaskFlow lesson:

Learn concept
    ↓
Understand concept
    ↓
Write code
    ↓
Run application
    ↓
Check output
    ↓
Test normal case
    ↓
Test edge case
    ↓
git status
    ↓
git diff
    ↓
mvn clean compile
    ↓
Commit
    ↓
Push

Learning Principle

TaskFlow is both:

Learning Project
+
Java Backend Portfolio Project

The goal is not simply to make the application run.

Each stage should build practical backend knowledge:

Java
 ↓
OOP
 ↓
Collections
 ↓
Generics
 ↓
Exceptions
 ↓
Optional
 ↓
Lambda
 ↓
Stream API
 ↓
Maven
 ↓
SQL / MySQL
 ↓
HTTP / REST
 ↓
Spring Boot
 ↓
JPA / Hibernate
 ↓
DTO / Validation
 ↓
Security / JWT
 ↓
Testing
 ↓
Redis
 ↓
RabbitMQ
 ↓
Docker
 ↓
CI/CD
 ↓
Advanced / Microservices / Cloud
 ↓
Backend Portfolio


Latest Checkpoint Summary

Java Foundation       ✅
Collections           ✅
Generics              ✅
Exceptions            ✅
Optional              ✅
Lambda                ✅
Stream API            ✅
Maven                 🔄
SQL / MySQL           🔄
HTTP / REST            🔄
Spring Boot            🔄
JPA / Hibernate        🔄

Today's stopping point:

Spring Boot
→ MySQL Docker
→ JPA Entity
→ Repository
→ Service
→ REST Controller
→ Create
→ Read
→ Update
→ Delete
→ Postman verification
→ Git commit
→ STOP FOR TODAY

Next time:

Tiếp tục TaskFlow
→ Improve CRUD error handling
→ 404 Not Found
→ Validation
→ DTO