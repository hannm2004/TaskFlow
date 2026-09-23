/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taskflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author HP
 */
public class Main {

    public static <T> void printValue(T value) {
        System.out.println("Value: " + value);
    }

    public static <T extends Task> void printTask(T task) {
        System.out.println(
                task.getId()
                + " | "
                + task.getTitle()
                + " | "
                + task.getTaskType()
        );
    }

    public static void testException() throws Exception {

        throw new Exception("Test error");
    }

    public static void main(String[] args) {
        try {

            testException();

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }

        List<Task> tasks = new ArrayList<>();

        Task task1 = new FeatureTask(
                1001L,
                "Implement Login",
                "Implement user authentication",
                "HIGH",
                100,
                "AUTHENTICATION"
        );

        Task task2 = new FeatureTask(
                1002L,
                "Design Database",
                "Design PostgreSQL database",
                "HIGH",
                70,
                "DATABASE"
        );

        BugTask bugTask = new BugTask(
                1003L,
                "Fix Login Error",
                "Fix authentication error",
                "URGENT",
                30,
                "CRITICAL"
        );

        Task normalTask = new FeatureTask(
                1004L,
                "Implement Login UI",
                "Implement login user interface",
                "HIGH",
                100,
                "UI"
        );

        Task polymorphicBugTask = new BugTask(
                1005L,
                "Fix Payment Error",
                "Fix payment processing error",
                "URGENT",
                30,
                "CRITICAL"
        );

        tasks.add(new FeatureTask(
                3001L,
                "Implement Login",
                "Implement user authentication",
                "HIGH",
                100,
                "AUTHENTICATION"
        ));

        tasks.add(new BugTask(
                3002L,
                "Fix Payment Bug",
                "Fix payment processing error",
                "URGENT",
                50,
                "CRITICAL"
        ));

        tasks.add(new FeatureTask(
                3003L,
                "Create REST API",
                "Create TaskFlow REST API",
                "MEDIUM",
                30,
                "BACKEND"
        ));

        tasks.add(new BugTask(
                3004L,
                "Fix Database Bug",
                "Fix database connection error",
                "HIGH",
                20,
                "HIGH"
        ));

        tasks.add(new FeatureTask(
                3005L,
                "Add Dark Mode",
                "Add dark mode for TaskFlow",
                "MEDIUM",
                40,
                "UI"
        ));

        tasks.add(new ImprovementTask(
                3006L,
                "Optimize Dashboard",
                "Improve dashboard performance",
                "HIGH",
                60,
                "PERFORMANCE"
        ));

        System.out.println("=== TASKFLOW ===");

        System.out.println("ID: " + task1.getId());
        System.out.println("Task: " + task1.getTitle());
        System.out.println("Description: " + task1.getDescription());
        System.out.println("Priority: " + task1.getPriority());
        System.out.println("Progress: " + task1.getProgress() + "%");
        System.out.println("Type: " + task1.getTaskType());

        System.out.println();

        System.out.println("ID: " + task2.getId());
        System.out.println("Task: " + task2.getTitle());
        System.out.println("Description: " + task2.getDescription());
        System.out.println("Priority: " + task2.getPriority());
        System.out.println("Progress: " + task2.getProgress() + "%");
        System.out.println("Type: " + task2.getTaskType());

        System.out.println();

        System.out.println("=== BUG TASK ===");

        System.out.println("ID: " + bugTask.getId());
        System.out.println("Task: " + bugTask.getTitle());
        System.out.println("Description: " + bugTask.getDescription());
        System.out.println("Priority: " + bugTask.getPriority());
        System.out.println("Progress: " + bugTask.getProgress() + "%");
        System.out.println("Severity: " + bugTask.getBugSeverity());
        System.out.println("Type: " + bugTask.getTaskType());

        System.out.println();

        System.out.println("=== POLYMORPHISM ===");

        System.out.println(
                "Feature task type: " + normalTask.getTaskType()
        );

        System.out.println(
                "Bug task type: " + polymorphicBugTask.getTaskType()
        );

        System.out.println();

        System.out.println("=== POLYMORPHISM TEST ===");

        Task task = new BugTask(
                2001L,
                "Fix Payment Bug",
                "Fix payment processing error",
                "URGENT",
                50,
                "HIGH"
        );

        System.out.println("Type: " + task.getTaskType());
        System.out.println("Title: " + task.getTitle());
        System.out.println("Progress: " + task.getProgress() + "%");

        task2.setProgress(80);

        System.out.println(
                "Task 2 new progress: "
                + task2.getProgress()
                + "%"
        );

        System.out.println();

        System.out.println("=== TASK LIST ===");

        for (Task taskItem : tasks) {

            System.out.println(
                    "ID: " + taskItem.getId()
                    + " | Title: " + taskItem.getTitle()
                    + " | Type: " + taskItem.getTaskType()
                    + " | Progress: " + taskItem.getProgress() + "%"
            );
        }
        System.out.println();

        System.out.println("=== INTERFACE TEST ===");

        Assignable assignableTask = new BugTask(
                4001L,
                "Fix Login Security Bug",
                "Fix authentication security issue",
                "URGENT",
                20,
                "CRITICAL"
        );

        assignableTask.assignTo("admin");

        Assignable featureAssignable = new FeatureTask(
                4002L,
                "Add User Profile",
                "Create user profile feature",
                "MEDIUM",
                0,
                "USER"
        );

        featureAssignable.assignTo("developer");

        System.out.println();
        System.out.println("=== ASSIGNABLE TASKS ===");

        List<Assignable> assignableTasks = new ArrayList<>();

        assignableTasks.add(new BugTask(
                5001L,
                "Fix Payment Bug",
                "Fix payment processing error",
                "URGENT",
                50,
                "CRITICAL"
        ));

        assignableTasks.add(new FeatureTask(
                5002L,
                "Add User Profile",
                "Create user profile feature",
                "MEDIUM",
                0,
                "USER"
        ));

        for (Assignable assignable : assignableTasks) {
            assignable.assignTo("developer");
        }

        System.out.println();
        System.out.println("=== LIST OPERATIONS ===");

        List<Task> taskList = new ArrayList<>();

        taskList.add(new FeatureTask(
                6001L,
                "Login",
                "Implement login feature",
                "HIGH",
                100,
                "AUTHENTICATION"
        ));

        taskList.add(new BugTask(
                6002L,
                "Fix Payment",
                "Fix payment bug",
                "URGENT",
                50,
                "CRITICAL"
        ));

        taskList.add(new ImprovementTask(
                6003L,
                "Optimize Database",
                "Improve database performance",
                "HIGH",
                70,
                "DATABASE"
        ));

        for (Task item : taskList) {

            System.out.println(
                    item.getId()
                    + " | "
                    + item.getTitle()
                    + " | "
                    + item.getTaskType()
            );
        }
        System.out.println();

        System.out.println(
                "Total tasks: " + taskList.size()
        );

        Task firstTask = taskList.get(0);

        System.out.println(
                "First task: " + firstTask.getTitle()
        );

        //taskList.remove(1);
        System.out.println(
                "Total tasks after remove: " + taskList.size()
        );

        long targetId = 6002L;

        Task foundTask = null;

        for (Task item : taskList) {

            if (item.getId() == targetId) {
                foundTask = item;
            }
        }

        if (foundTask != null) {

            System.out.println(
                    "Found task: " + foundTask.getTitle()
            );

        } else {

            System.out.println("Task not found");
        }

        System.out.println();
        System.out.println("=== SET OPERATIONS ===");

        Set<String> tags = new HashSet<>();

        tags.add("java");
        tags.add("backend");
        tags.add("spring");
        tags.add("java");

        System.out.println(tags);

        System.out.println("Total tags: " + tags.size());

        System.out.println(
                "Has Java tag: " + tags.contains("java")
        );

        System.out.println(
                "Has Docker tag: " + tags.contains("docker")
        );

        tags.remove("backend");

        System.out.println();
        System.out.println("After remove: " + tags);
        System.out.println("Total tags: " + tags.size());

        System.out.println();
        System.out.println("Tags:");

        for (String tag : tags) {
            System.out.println("- " + tag);
        }
        System.out.println();
        System.out.println("=== MAP OPERATIONS ===");

        Map<Long, String> taskMap = new HashMap<>();

        taskMap.put(1001L, "Implement Login");
        taskMap.put(1002L, "Design Database");
        taskMap.put(1003L, "Fix Login Error");

        System.out.println(taskMap);

        System.out.println(
                "Task 1002: " + taskMap.get(1002L)
        );

        System.out.println(
                "Has task 1002: " + taskMap.containsKey(1002L)
        );

        System.out.println(
                "Has task 9999: " + taskMap.containsKey(9999L)
        );

        System.out.println(
                "Has value Design Database: "
                + taskMap.containsValue("Design Database")
        );

        System.out.println(
                "Has value Docker: "
                + taskMap.containsValue("Docker")
        );

        taskMap.remove(1002L);

        System.out.println();
        System.out.println("After remove: " + taskMap);
        System.out.println(
                "Total tasks in map: " + taskMap.size()
        );
        System.out.println();
        System.out.println("Tasks in map:");

        for (Map.Entry<Long, String> entry : taskMap.entrySet()) {

            System.out.println(
                    entry.getKey()
                    + " → "
                    + entry.getValue()
            );
        }

        System.out.println();
        System.out.println("=== GENERICS BASIC ===");

        List<String> usernames = new ArrayList<>();

        usernames.add("han");
        usernames.add("admin");
        usernames.add("developer");

        for (String username : usernames) {
            System.out.println(username);
        }

        System.out.println();
        System.out.println("=== GENERIC CLASS ===");

        Box<String> stringBox = new Box<>("Hello TaskFlow");

        System.out.println(
                "String box: " + stringBox.getValue()
        );

        Box<Integer> integerBox = new Box<>(100);

        System.out.println(
                "Integer box: " + integerBox.getValue()
        );

        System.out.println();
        System.out.println("=== GENERIC METHOD ===");

        printValue("Hello TaskFlow");
        printValue(100);
        printValue(99.5);

        System.out.println();
        System.out.println("=== GENERIC TASK METHOD ===");

        FeatureTask featureTask = new FeatureTask(
                7001L,
                "User Profile",
                "Implement user profile",
                "HIGH",
                60,
                "ACCOUNT"
        );

        BugTask genericBugTask = new BugTask(
                7002L,
                "Fix Login",
                "Fix login error",
                "URGENT",
                40,
                "CRITICAL"
        );

        printTask(featureTask);
        printTask(genericBugTask);

        System.out.println();
        System.out.println("=== GENERIC REPOSITORY ===");

        GenericRepository<Task> repository = new GenericRepository<>();

        repository.add(task1);
        repository.add(task2);
        repository.add(bugTask);

        for (Task item : repository.getAll()) {
            System.out.println(
                    item.getId()
                    + " | "
                    + item.getTitle()
                    + " | "
                    + item.getTaskType()
            );
        }

        System.out.println();
        System.out.println("=== EXCEPTION TEST ===");

        Task exceptionTask = new FeatureTask(
                8001L,
                "Test Exception",
                "Test exception handling",
                "HIGH",
                50,
                "TEST"
        );

        try {

            exceptionTask.setProgress(150);

        } catch (InvalidTaskProgressException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

        } finally {

            System.out.println("Exception test finished");
        }

        System.out.println("Program continues...");

        System.out.println();
        System.out.println("=== FIND TASK BY ID ===");

        try {

            Task repositoryTask = repository.findById(1001L);

            System.out.println(
                    "Found task: "
                    + repositoryTask.getTitle()
            );

        } catch (TaskNotFoundException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }

        System.out.println();
        System.out.println("=== OPTIONAL BASIC ===");

        String username = "han";

        Optional<String> optionalUsername
                = Optional.of(username);

        System.out.println(
                "Username: " + optionalUsername.get()
        );

        System.out.println();
        System.out.println("=== OPTIONAL EMPTY ===");

        Optional<String> emptyUsername
                = Optional.empty();

        System.out.println(
                "Is username present: "
                + emptyUsername.isPresent()
        );

        System.out.println();
        System.out.println("=== OPTIONAL OR ELSE GET ===");

        Optional<String> optionalGuest
                = Optional.empty();

        String guestResult
                = optionalGuest.orElseGet(() -> "Guest");

        System.out.println(
                "Username: " + guestResult
        );

        System.out.println();
        System.out.println("=== OPTIONAL TASK ===");

        Optional<Task> optionalTask
                = repository.findOptionalById(1001L);

        System.out.println(
                "Task found: "
                + optionalTask.isPresent()
        );

        System.out.println();
        System.out.println("=== OPTIONAL TASK NOT FOUND ===");

        Optional<Task> missingTask
                = repository.findOptionalById(9999L);

        System.out.println(
                "Task found: "
                + missingTask.isPresent()
        );

        System.out.println();
        System.out.println("=== OPTIONAL OR ELSE THROW TASK ===");

        Task existingTask
                = repository
                        .findOptionalById(1001L)
                        .orElseThrow(
                                () -> new TaskNotFoundException(
                                        "Task not found"
                                )
                        );

        System.out.println(
                "Task: " + existingTask.getTitle()
        );

        System.out.println();
        System.out.println("=== OPTIONAL IF PRESENT ===");

        Optional<Task> taskForIfPresent
                = repository.findOptionalById(1001L);

        taskForIfPresent.ifPresent(
                optionalIfPresentTask -> System.out.println(
                        "Task title: " + optionalIfPresentTask.getTitle()
                )
        );

        System.out.println();
        System.out.println("=== OPTIONAL MAP ===");

        Optional<Task> taskForMap
                = repository.findOptionalById(1001L);

        Optional<String> taskTitle
                = taskForMap.map(
                        optionalMapTask -> optionalMapTask.getTitle()
                );

        System.out.println(
                "Task title: " + taskTitle.orElse("Unknown")
        );

        System.out.println();
        System.out.println("=== OPTIONAL FILTER ===");

        Optional<Task> taskForFilter
                = repository.findOptionalById(1003L);

        Optional<Task> highProgressTask
                = taskForFilter.filter(
                        optionalFilterTask
                        -> optionalFilterTask.getProgress() >= 80
                );

        System.out.println(
                "High progress task: "
                + highProgressTask.isPresent()
        );

        System.out.println();
        System.out.println("=== OPTIONAL MAP AND FILTER ===");

        Optional<Task> taskForMapFilter
                = repository.findOptionalById(1003L);

        String mapFilterResult
                = taskForMapFilter
                        .filter(
                                optionalMapFilterTask
                                -> optionalMapFilterTask.getProgress() >= 80
                        )
                        .map(
                                optionalMapFilterTask
                                -> optionalMapFilterTask.getTitle()
                        )
                        .orElse("Task does not meet condition");

        System.out.println(
                "Result: " + mapFilterResult
        );
    }
}
