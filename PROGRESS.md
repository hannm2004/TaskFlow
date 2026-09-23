# TaskFlow Learning Progress

## Project

* Project: TaskFlow
* Direction: Java Backend + Docker
* Repository: GitHub TaskFlow
* Local path: `D:\TaskFlow\taskflow`
* Java: JDK 21
* Build tool: Maven
* Main package: `com.taskflow`

---

## Learning Roadmap

Java Foundation
→ Collections & Generics
→ Exceptions & Optional
→ Maven
→ SQL / PostgreSQL
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

---

# Current Checkpoint

## Java Foundation

* [x] Variables / Data Types
* [x] Operators
* [x] If / Else
* [x] Validation
* [x] Methods
* [x] Loops
* [x] Arrays
* [x] Class & Object
* [x] Constructor
* [x] Encapsulation
* [x] Inheritance
* [x] Polymorphism
* [x] Abstraction
* [x] Interface

## Collections

* [x] Collections

  * [x] List
  * [x] Set
  * [x] Map

## Generics

* [x] Generic Type `<T>`
* [x] Generic Class `Box<T>`
* [x] Diamond Operator `<>`
* [x] Generic Method `<T>`
* [x] Bounded Generic `<T extends Task>`
* [x] Generic Repository `GenericRepository<T>`
* [x] Bounded Generic Repository `GenericRepository<T extends Task>`

## Exceptions

* [x] `try`
* [x] `catch`
* [x] `finally`
* [x] `throw`
* [x] `throws`
* [x] Checked Exception
* [x] Unchecked Exception
* [x] Custom Exception
* [x] `InvalidTaskProgressException`
* [x] `TaskNotFoundException`
* [x] Exception handling practice
* [x] Exception handling in `GenericRepository`
* [x] `findById()` with custom exception

## Next Java Topics

* [ ] Optional
* [ ] Lambda
* [ ] Stream API

---

# Current Lesson

## Java Exceptions — Completed

The Java Exception checkpoint has been completed.

### Completed

* Learned the basic concept of Exception
* Practiced `try-catch`
* Practiced `finally`
* Learned `throw`
* Learned `throws`
* Distinguished `throw` vs `throws`
* Learned Checked Exception
* Learned Unchecked Exception
* Created Custom Exceptions for TaskFlow:

  * `InvalidTaskProgressException`
  * `TaskNotFoundException`
* Applied `InvalidTaskProgressException` to `Task.setProgress()`
* Applied `TaskNotFoundException` to `GenericRepository.findById()`
* Practiced handling custom exceptions with `try-catch`
* Practiced handling invalid Task progress
* Practiced handling a Task that does not exist
* Verified Exception flow with Maven

---

# Exception Summary

## `try`

Code that may cause an Exception.

```text
try
→ Code that may cause an Exception
```

## `catch`

Catches and handles the Exception.

```text
catch
→ Catches and handles the Exception
```

## `finally`

Code that runs after `try/catch`.

```text
finally
→ Code that runs after try/catch
```

## `throw`

Actively throws an Exception.

```text
throw
→ Actively throws an Exception
```

## `throws`

Declares that a method may throw an Exception.

```text
throws
→ Declares that a method may throw an Exception
```

---

# TaskFlow Custom Exceptions

## `InvalidTaskProgressException`

Used when Task progress is outside the valid range.

```text
progress < 0
        OR
progress > 100
```

Flow:

```text
Task.setProgress()
        ↓
Invalid progress
        ↓
throw InvalidTaskProgressException
        ↓
catch InvalidTaskProgressException
        ↓
Handle error
```

---

## `TaskNotFoundException`

Used when a Task cannot be found by ID.

Class:

```java
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message) {
        super(message);
    }
}
```

---

# Generic Repository

Current repository:

```java
public class GenericRepository<T extends Task> {

    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getAll() {
        return items;
    }

    public T findById(long id) {

        for (T item : items) {

            if (item.getId() == id) {
                return item;
            }
        }

        throw new TaskNotFoundException(
                "Task with ID " + id + " not found"
        );
    }
}
```

## `findById()` Flow

```text
repository.findById(id)
        ↓
Loop through Task list
        ↓
Check item.getId()
        ↓
ID found?
   ↙           ↘
 YES            NO
  ↓              ↓
return item    throw
               TaskNotFoundException
```

Example:

```java
Task repositoryTask = repository.findById(1001L);
```

If the Task exists:

```text
Found task: Implement Login
```

If the Task does not exist:

```java
repository.findById(9999L);
```

Result:

```text
Error: Task with ID 9999 not found
```

---

# Collections Summary

## List

* Ordered collection
* Allows duplicate elements
* Practiced `ArrayList`
* Practiced `add()`
* Practiced `size()`
* Practiced `get()`
* Practiced `remove()`
* Practiced searching for a Task by ID
* Practiced iterating through a List

Example:

```java
List<Task> taskList = new ArrayList<>();
```

---

## Set

* Collection that does not allow duplicate elements
* Practiced `HashSet`
* Practiced `add()`
* Practiced `size()`
* Practiced `contains()`
* Practiced `remove()`
* Practiced iterating through Set

Example:

```java
Set<String> tags = new HashSet<>();
```

---

## Map

* Stores data as `key -> value`
* Practiced `HashMap`
* Practiced `put()`
* Practiced `get()`
* Practiced `remove()`
* Practiced `containsKey()`
* Practiced `containsValue()`
* Practiced `Map.Entry`
* Practiced `entrySet()`
* Practiced iterating through Map

Example:

```java
Map<Long, String> taskMap = new HashMap<>();
```

---

# Generics Summary

## Generic Type

Generic types allow classes and methods to work with different data types.

Example:

```java
List<String>
List<Task>
```

---

## Generic Class

`Box<T>` can work with different data types.

```java
Box<String>
Box<Integer>
```

Example:

```java
Box<String> stringBox = new Box<>("Hello TaskFlow");

Box<Integer> integerBox = new Box<>(100);
```

---

## Diamond Operator

```java
new ArrayList<>()
```

The compiler can infer the generic type.

---

## Generic Method

```java
public static <T> void printValue(T value) {
    System.out.println("Value: " + value);
}
```

The method can accept different types.

Examples:

```java
printValue("Hello TaskFlow");
printValue(100);
printValue(99.5);
```

---

## Bounded Generic

```java
<T extends Task>
```

The generic type must be `Task` or a subclass of `Task`.

---

## Generic Repository

```java
GenericRepository<Task> repository =
        new GenericRepository<>();
```

The repository stores Task objects using:

```java
List<T>
```

The repository is restricted to:

```java
GenericRepository<T extends Task>
```

This ensures that `T` must be a `Task` or subclass of `Task`.

---

# Java OOP Summary

## Class & Object

* Created classes
* Created objects
* Practiced object interaction

## Constructor

* Created constructors
* Initialized object state through constructors

## Encapsulation

* Used `private` fields
* Used getters
* Used setters
* Added validation inside setters

## Inheritance

* Used `extends`
* Created subclasses from `Task`

Examples:

```text
Task
├── FeatureTask
├── BugTask
└── ImprovementTask
```

## Polymorphism

Used:

```java
Task task = new BugTask(...);
```

The reference type is `Task`, while the actual object is `BugTask`.

## Abstraction

`Task` is an abstract class.

## Interface

Created:

```java
public interface Assignable {
    void assignTo(String username);
}
```

Implemented by:

```text
FeatureTask
BugTask
```

---

# TaskFlow Domain Model

Current basic Task hierarchy:

```text
                    Task
                     │
        ┌────────────┼────────────┐
        ↓            ↓            ↓
 FeatureTask      BugTask    ImprovementTask
        │            │
        └────── Assignable ──────┘
```

`Task` contains common information:

```text
id
title
description
priority
progress
```

`FeatureTask` adds:

```text
featureCategory
```

`BugTask` adds:

```text
bugSeverity
```

`ImprovementTask` adds:

```text
improvementArea
```

---

# Important Project Files

Current important classes:

```text
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
└── TaskNotFoundException.java
```

---

# Git

## Branch Strategy

Current branch:

```text
feature/java-foundation
```

Branch roles:

```text
main
→ stable

develop
→ development

feature/*
→ individual learning / feature branches
```

---

## Latest Checkpoints

Completed checkpoints include:

* `feat: practice task list collections`
* `feat: practice set collections`
* `feat: practice map collections`
* `docs: update TaskFlow learning progress`
* `feat: practice generics and custom exceptions`
* Exception handling practice
* `TaskNotFoundException`
* `GenericRepository.findById()`

---

## Current Git State

Expected branch:

```text
feature/java-foundation
```

The Java Foundation learning branch contains the completed:

```text
Java Foundation
        ↓
Collections
        ↓
Generics
        ↓
Exceptions
```

---

# Working Tree Checklist

Before committing:

```powershell
git status
```

Check changed code:

```powershell
git diff
```

Compile:

```powershell
mvn clean compile
```

Run application:

```powershell
mvn exec:java "-Dexec.mainClass=com.taskflow.Main"
```

Check status again:

```powershell
git status
```

Review changes:

```powershell
git diff --stat
```

---

# Standard Verification Flow

For each new TaskFlow lesson:

```text
Write code
    ↓
Run application
    ↓
Check output
    ↓
Test normal case
    ↓
Test error / edge case
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
```

---

# Current Progress

```text
Java Foundation        ✅ Completed
Collections            ✅ Completed
Generics               ✅ Completed
Exceptions             ✅ Completed

Optional               ⏳ Next
Lambda                 ⏳ Later
Stream API             ⏳ Later
Maven                  ⏳ Later
SQL / PostgreSQL       ⏳ Later
HTTP / REST            ⏳ Later
Spring Boot            ⏳ Later
JPA / Hibernate        ⏳ Later
DTO / Validation       ⏳ Later
Security / JWT         ⏳ Later
Testing                ⏳ Later
Redis                  ⏳ Later
RabbitMQ               ⏳ Later
Docker                 ⏳ Later
CI/CD                  ⏳ Later
Advanced / Cloud       ⏳ Later
```

---

# Current Stop Point

## Java Exception Checkpoint — COMPLETED

Completed:

```text
try
catch
finally
throw
throws
Checked Exception
Unchecked Exception
Custom Exception
InvalidTaskProgressException
TaskNotFoundException
GenericRepository.findById()
Exception handling practice
```

---

# Next Learning Checkpoint

## Optional

The next topic is:

```text
Java Optional
```

Learning direction:

```text
Why null can be problematic
        ↓
Optional<T>
        ↓
Optional.of()
        ↓
Optional.ofNullable()
        ↓
Optional.empty()
        ↓
isPresent()
        ↓
orElse()
        ↓
orElseGet()
        ↓
orElseThrow()
        ↓
Optional with Task
        ↓
Optional in Repository
```

The next lesson should be learned one small concept at a time.

Do not skip directly to Lambda or Stream API before completing the Optional checkpoint.

---

# How To Continue

When starting a new chat:

```text
Tiếp tục TaskFlow
```

Then:

1. Check the current Git status.
2. Check the latest Git log.
3. Read `PROGRESS.md` if needed.
4. Continue from the current checkpoint.
5. Do not restart completed lessons.
6. Keep learning flexible by milestone/checkpoint, not fixed calendar days.
7. Learn one small concept at a time.
8. Run and verify each exercise before moving to the next concept.
9. Keep meaningful Git commits.
10. Push completed checkpoints to `origin/feature/java-foundation`.

---

# Learning Principle

TaskFlow is being developed as both:

```text
Learning Project
+
Java Backend Portfolio Project
```

The goal is not only to make the program run.

Each checkpoint should build understanding of:

```text
Java fundamentals
        ↓
Object-Oriented Programming
        ↓
Collections
        ↓
Generics
        ↓
Exception Handling
        ↓
Maven
        ↓
SQL / PostgreSQL
        ↓
HTTP / REST
        ↓
Spring Boot
        ↓
Database / JPA
        ↓
Security
        ↓
Testing
        ↓
Docker
        ↓
CI/CD
        ↓
Backend Portfolio
```

---

# Latest Session Summary

Completed in the current Exception checkpoint:

* Created `TaskNotFoundException`
* Extended `RuntimeException`
* Added `findById(long id)` to `GenericRepository<T extends Task>`
* Returned the Task when the ID exists
* Threw `TaskNotFoundException` when the ID does not exist
* Handled `TaskNotFoundException` in `Main`
* Tested an existing Task ID
* Tested a non-existing Task ID
* Fixed duplicate `foundTask` variable issue
* Verified the project compiles and runs

Current next step:

```text
Optional
```
