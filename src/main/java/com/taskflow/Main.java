/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taskflow;

/**
 *
 * @author HP
 */
public class Main {

    public static void main(String[] args) {

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

        task2.setProgress(80);

        System.out.println(
                "Task 2 new progress: "
                + task2.getProgress()
                + "%"
        );

    }
}