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

## Current Checkpoint

### Java Foundation

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

### Collections

* [x] Collections

  * [x] List
  * [x] Set
  * [x] Map

### Generics

* [x] Generic Type `<T>`
* [x] Generic Class `Box<T>`
* [x] Diamond Operator `<>`
* [x] Generic Method `<T>`
* [x] Bounded Generic `<T extends Task>`
* [x] Generic Repository `GenericRepository<T>`
* [x] Bounded Generic Repository `GenericRepository<T extends Task>`

### Exceptions

* [x] `try`
* [x] `catch`
* [x] `finally`
* [x] `throw`
* [x] `throws`
* [x] Checked Exception
* [x] Unchecked Exception
* [x] Custom Exception — introduced
* [ ] Complete Custom Exception practice
* [ ] `TaskNotFoundException`
* [ ] Exception handling practice

### Next Java Topics

* [ ] Optional
* [ ] Lambda
* [ ] Stream API

---

## Current Lesson

Java Exceptions — Custom Exception

### Completed

* Learned the basic concept of Exception
* Practiced `try-catch`
* Practiced `finally`
* Learned `throw`
* Learned `throws`
* Distinguished `throw` vs `throws`
* Learned Checked Exception
* Learned Unchecked Exception
* Created a Custom Exception for TaskFlow:

  * `InvalidTaskProgressException`
* Applied Custom Exception to `Task.setProgress()`
* Used `InvalidTaskProgressException` with `try-catch`
* Verified the Exception flow concept with Maven

### Exception Summary

```text
try
→ Code that may cause an Exception

catch
→ Catches and handles the Exception

finally
→ Code that runs after try/catch

throw
→ Actively throws an Exception

throws
→ Declares that a method may throw an Exception
```

### TaskFlow Custom Exception

`InvalidTaskProgressException` is used when:

```text
progress < 0
        OR
progress > 100
```

Current concept:

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

### Current Stop Point

The current learning session stops after introducing and practicing:

```text
InvalidTaskProgressException
```

The next exercise is to create another TaskFlow-specific Custom Exception independently, such as:

```text
TaskNotFoundException
```

---

## Collections Summary

### List

* Ordered collection
* Allows duplicate elements
* Practiced `ArrayList`
* Practiced `add()`, `size()`, `get()`, `remove()`
* Practiced searching for a Task by ID

### Set

* Collection that does not allow duplicate elements
* Practiced `HashSet`
* Practiced `add()`, `size()`, `contains()`, `remove()`
* Practiced iterating through Set

### Map

* Stores data as `key -> value`
* Practiced `HashMap`
* Practiced `put()`, `get()`, `remove()`
* Practiced `containsKey()`
* Practiced `containsValue()`
* Practiced `Map.Entry`
* Practiced `entrySet()`

---

## Generics Summary

### Generic Class

```java
Box<String>
Box<Integer>
```

The same class can work with different data types.

### Generic Method

```java
public static <T> void printValue(T value)
```

The method can accept different types.

### Bounded Generic

```java
<T extends Task>
```

The generic type must be `Task` or a subclass of `Task`.

### Generic Repository

```java
GenericRepository<Task> repository =
        new GenericRepository<>();
```

The repository stores Task objects using:

```java
List<T>
```

The repository was then restricted to:

```java
GenericRepository<T extends Task>
```

---

## Git

Current branch:

`feature/java-foundation`

Main:

* `main` = stable
* `develop` = development
* `feature/*` = individual learning/feature branches

### Latest Known Checkpoints

* `feat: practice task list collections`
* `feat: practice set collections`
* `feat: practice map collections`
* `docs: update TaskFlow learning progress`
* Generics practice completed
* Exceptions learning currently in progress

### Working Tree

Current learning progress:

* Java Foundation completed
* Collections completed
* Generics completed
* Exceptions in progress
* Current stop point: Custom Exception

