# TaskFlow Learning Progress

## Project

* Project: TaskFlow
* Direction: Java Backend + Docker
* Repository: GitHub TaskFlow
* Local path: `D:\TaskFlow\taskflow`
* Java: JDK 21
* Build tool: Maven
* Main package: `com.taskflow`
* Current branch: `feature/java-foundation`

---

# Learning Roadmap

```text
Java Foundation
→ Collections & Generics
→ Exceptions & Optional
→ Lambda & Stream API
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
```

---

# Current Progress

```text
Java Foundation        ✅ Completed
Collections            ✅ Completed
Generics               ✅ Completed
Exceptions             ✅ Completed
Optional               ✅ Completed
Lambda                 ✅ Completed
Stream API             🔄 In Progress
Maven                  ⏳ Later
SQL / PostgreSQL       ⏳ Later
HTTP / REST            ⏳ Later
Spring Boot            ⏳ Later
JPA / Hibernate        ⏳ Later
DTO / Validation       ⏳ Later
Security / JWT         ⏳ Later
Testing                 ⏳ Later
Redis                  ⏳ Later
RabbitMQ               ⏳ Later
Docker                 ⏳ Later
CI/CD                  ⏳ Later
Advanced / Cloud       ⏳ Later
```

---

# Java Foundation

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

---

# Collections

* [x] Collections

  * [x] List
  * [x] Set
  * [x] Map

### List

* [x] `ArrayList`
* [x] `add()`
* [x] `size()`
* [x] `get()`
* [x] `remove()`
* [x] Searching Task by ID
* [x] Iterating through List

### Set

* [x] `HashSet`
* [x] `add()`
* [x] `size()`
* [x] `contains()`
* [x] `remove()`
* [x] Iterating through Set
* [x] Duplicate prevention

### Map

* [x] `HashMap`
* [x] `put()`
* [x] `get()`
* [x] `remove()`
* [x] `containsKey()`
* [x] `containsValue()`
* [x] `Map.Entry`
* [x] `entrySet()`
* [x] Iterating through Map

---

# Generics

* [x] Generic Type `<T>`
* [x] Generic Class `Box<T>`
* [x] Diamond Operator `<>`
* [x] Generic Method `<T>`
* [x] Bounded Generic `<T extends Task>`
* [x] Generic Repository `GenericRepository<T>`
* [x] Bounded Generic Repository

Example:

```java
public class GenericRepository<T extends Task>
```

---

# Exceptions

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

---

# Optional

## Status

✅ Completed

* [x] Why `null` can be problematic
* [x] `Optional<T>`
* [x] `Optional.of()`
* [x] `Optional.ofNullable()`
* [x] `Optional.empty()`
* [x] `isPresent()`
* [x] `orElse()`
* [x] `orElseGet()`
* [x] `orElseThrow()`
* [x] Optional with Task
* [x] Optional in Repository
* [x] Optional `ifPresent()`
* [x] Optional `map()`
* [x] Optional `filter()`
* [x] Optional `map()` + `filter()`
* [x] `orElseThrow()` with Task

Current repository method:

```java
public Optional<T> findOptionalById(long id)
```

---

# Lambda

## Status

✅ Completed

* [x] Lambda expression basic
* [x] Lambda with one parameter
* [x] Lambda with block body
* [x] Lambda with `if`
* [x] Lambda with Task
* [x] `@FunctionalInterface`
* [x] Custom functional interface `TaskAction`
* [x] `Consumer<T>`
* [x] `Predicate<T>`
* [x] `Function<T, R>`
* [x] Lambda with List
* [x] Lambda with Task collection

Example:

```java
tasks.forEach(
        taskItem -> System.out.println(
                taskItem.getTitle()
        )
);
```

---

# Stream API

## Status

🔄 In Progress

The Stream API has been practiced using the TaskFlow `tasks` collection.

---

## Basic Operations

* [x] `stream()`
* [x] `forEach()`
* [x] `filter()`
* [x] `map()`
* [x] `sorted()`
* [x] `distinct()`
* [x] `limit()`

---

## Stream Combinations

* [x] `filter()` + `sorted()`
* [x] `filter()` + `sorted()` + `limit()`
* [x] `filter()` + `map()`
* [x] Stream + Lambda

---

## Collect Operations

* [x] `Collectors.toList()`
* [x] Collect filtered Tasks to List
* [x] Collect Task titles to List
* [x] `Collectors.toSet()`
* [x] Collect Task Types to Set
* [x] `Collectors.toMap()`
* [x] Collect Task ID → Title to Map

Example:

```java
Map<Long, String> taskTitleMap =
        tasks.stream()
                .collect(
                        Collectors.toMap(
                                taskItem -> taskItem.getId(),
                                taskItem -> taskItem.getTitle()
                        )
                );
```

---

## Counting Operations

* [x] `count()`
* [x] `filter()` + `count()`

Example:

```java
long highProgressCount =
        tasks.stream()
                .filter(
                        taskItem -> taskItem.getProgress() >= 50
                )
                .count();
```

---

## Matching Operations

* [x] `anyMatch()`
* [x] `allMatch()`
* [x] `noneMatch()`

### `anyMatch()`

Checks whether at least one element satisfies the condition.

```java
boolean hasCompletedTask =
        tasks.stream()
                .anyMatch(
                        taskItem -> taskItem.getProgress() == 100
                );
```

Result:

```text
Has completed task: true
```

---

### `allMatch()`

Checks whether every element satisfies the condition.

```java
boolean allTasksCompleted =
        tasks.stream()
                .allMatch(
                        taskItem -> taskItem.getProgress() == 100
                );
```

Result:

```text
All tasks completed: false
```

---

### `noneMatch()`

Checks whether no element satisfies the condition.

```java
boolean hasNoCompletedTask =
        tasks.stream()
                .noneMatch(
                        taskItem -> taskItem.getProgress() == 100
                );
```

Current TaskFlow data contains a task with `100%` progress, therefore:

```text
No completed task: false
```

---

# Finding Operations

* [x] `findFirst()`
* [x] `findFirst()` + `filter()`
* [x] `findFirst()` + `Optional`
* [x] `findFirst()` + `Optional.map()`
* [x] `findFirst()` + `Optional.orElse()`
* [x] `findAny()`
* [x] `findAny()` + `filter()`

Example:

```java
Optional<Task> firstHighProgressTask =
        tasks.stream()
                .filter(
                        taskItem -> taskItem.getProgress() >= 50
                )
                .findFirst();
```

Optional handling:

```java
String noCompletedTaskTitle
        = noCompletedTask
                .map(
                        taskItem -> taskItem.getTitle()
                )
                .orElse("No task found");
```

---

# Current Stream API Checkpoint

Completed:

```text
Stream Basic                         ✅
Filter                               ✅
Map                                  ✅
Sorted                               ✅
Filter + Sorted                      ✅
Distinct                             ✅
Limit                                ✅
Filter + Sorted + Limit              ✅
Collect → List                       ✅
Collect → Titles                     ✅
Collect → Set                        ✅
Collect → Map                        ✅
Count                                ✅
Count + Filter                       ✅
anyMatch                             ✅
allMatch                             ✅
noneMatch                            ✅
findFirst                            ✅
findFirst + filter                   ✅
findFirst + Optional                 ✅
findAny                              ✅
findAny + filter                     ✅
```

---

# Latest Session — 2026-09-23

Completed during this session:

* [x] `findAny()`
* [x] `findAny()` + `filter()`
* [x] `noneMatch()`
* [x] Fixed duplicate variable name `noCompletedTask`
* [x] Renamed `noneMatch()` result variable to `hasNoCompletedTask`
* [x] Successfully ran the project
* [x] Verified `BUILD SUCCESS`

Important lesson from the duplicate variable error:

```text
noCompletedTask
→ Optional<Task>

hasNoCompletedTask
→ boolean
```

These variables represent different concepts and therefore should have different names.

---

# Current Stop Point

## Stream API — Match & Find Operations

Current completed point:

```text
noneMatch()
        ↓
BUILD SUCCESS
        ↓
STOP FOR TODAY
```

The project is currently paused here.

---

# Next Learning Session

Continue from:

```text
Stream API
```

Do **not** restart Optional or Lambda.

Next step should continue with the remaining useful Stream API concepts, one small concept at a time.

After the Stream API checkpoint is completed:

```text
Stream API
    ↓
Verify all exercises
    ↓
git status
    ↓
git diff
    ↓
mvn clean compile
    ↓
Commit Stream checkpoint
    ↓
Push origin/feature/java-foundation
    ↓
Update PROGRESS.md
```

Then move to:

```text
Maven
```

---

# Important Project Files

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
├── TaskNotFoundException.java
└── TaskAction.java
```

---

# TaskFlow Domain Model

```text
                    Task
                     │
        ┌────────────┼────────────┐
        ↓            ↓            ↓
 FeatureTask      BugTask    ImprovementTask
        │            │
        └────── Assignable ──────┘
```

`Task`:

```text
id
title
description
priority
progress
```

`FeatureTask`:

```text
featureCategory
```

`BugTask`:

```text
bugSeverity
```

`ImprovementTask`:

```text
improvementArea
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

    public Optional<T> findOptionalById(long id) {

        for (T item : items) {

            if (item.getId() == id) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }
}
```

---

# Git Branch Strategy

```text
main
→ stable

develop
→ development

feature/*
→ individual learning / feature branches
```

Current branch:

```text
feature/java-foundation
```

---

# Git Verification

Before committing:

```powershell
git status
```

Review changes:

```powershell
git diff
```

Compile:

```powershell
mvn clean compile
```

Run:

```powershell
mvn exec:java "-Dexec.mainClass=com.taskflow.Main"
```

Review:

```powershell
git diff --stat
```

Then commit and push only after verification.

---

# Standard Learning Flow

For every TaskFlow lesson:

```text
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
```

---

# Learning Principle

TaskFlow is both:

```text
Learning Project
+
Java Backend Portfolio Project
```

The goal is not simply to make the application run.

Each stage should build practical backend knowledge:

```text
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
SQL / PostgreSQL
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
```

---

# How To Continue

When starting a new chat:

```text
Tiếp tục TaskFlow
```

Then:

1. Check the current Git status.
2. Check the latest Git log.
3. Read `PROGRESS.md` if necessary.
4. Continue from the latest checkpoint.
5. Do not restart completed lessons.
6. Learn flexibly by milestone/checkpoint rather than fixed calendar days.
7. Learn one small concept at a time.
8. Run and verify each exercise before moving forward.
9. Keep meaningful Git commits.
10. Push completed checkpoints to `origin/feature/java-foundation`.

---

# Latest Checkpoint Summary

```text
Java Foundation        ✅
Collections            ✅
Generics               ✅
Exceptions             ✅
Optional               ✅
Lambda                 ✅
Stream API             🔄
                         ↑
                    CURRENT
```

Today's stopping point:

```text
Stream API
→ findAny()
→ findAny() + filter()
→ noneMatch()
→ BUILD SUCCESS
```

Next time:

```text
Tiếp tục Stream API
```

Do not restart from the beginning.
