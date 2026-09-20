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

        Task task1 = new Task(
                1001L,
                "Implement Login",
                "Implement user authentication",
                "HIGH",
                100
        );

        Task task2 = new Task(
                1002L,
                "Design Database",
                "Design PostgreSQL database",
                "HIGH",
                70
        );
        
        BugTask bugTask = new BugTask(
                1003L,
                "Fix Login Error",
                "Fix authentication error",
                "URGENT",
                30,
                "CRITICAL"
        );
        
        Task normalTask = new Task(
                1001L,
                "Implement Login",
                "Implement login feature",
                "HIGH",
                100
        );

        Task polymorphicBugTask = new BugTask(
                1003L,
                "Fix Login Error",
                "Fix authentication error",
                "URGENT",
                30,
                "CRITICAL"
        );
        
        tasks.add(new Task(
                3001L,
                "Implement Login",
                "Implement user authentication",
                "HIGH",
                100
        ));

        tasks.add(new BugTask(
                3002L,
                "Fix Payment Bug",
                "Fix payment processing error",
                "URGENT",
                50,
                "CRITICAL"
        ));

        tasks.add(new Task(
                3003L,
                "Create REST API",
                "Create TaskFlow REST API",
                "MEDIUM",
                30
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

        System.out.println();

        System.out.println("ID: " + task2.getId());
        System.out.println("Task: " + task2.getTitle());
        System.out.println("Description: " + task2.getDescription());
        System.out.println("Priority: " + task2.getPriority());
        System.out.println("Progress: " + task2.getProgress() + "%");

        System.out.println();

        System.out.println("=== BUG TASK ===");
        System.out.println("ID: " + bugTask.getId());
        System.out.println("Task: " + bugTask.getTitle());
        System.out.println("Description: " + bugTask.getDescription());
        System.out.println("Priority: " + bugTask.getPriority());
        System.out.println("Progress: " + bugTask.getProgress() + "%");
        System.out.println("Severity: " + bugTask.getBugSeverity());
        
        System.out.println();
        
        System.out.println("=== POLYMORPHISM ===");
        System.out.println(
                "Normal task type: " + normalTask.getTaskType()
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

    }
}