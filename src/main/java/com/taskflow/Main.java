/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taskflow;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Main {

    public static void main(String[] args) {

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
    }
}
