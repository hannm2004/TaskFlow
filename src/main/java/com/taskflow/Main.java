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

        Task task1 = new Task();

        task1.id = 1001L;
        task1.title = "Implement Login";
        task1.description = "Implement user authentication";
        task1.priority = "HIGH";
        task1.progress = 100;

        System.out.println("=== TASKFLOW ===");
        System.out.println("ID: " + task1.id);
        System.out.println("Task: " + task1.title);
        System.out.println("Description: " + task1.description);
        System.out.println("Priority: " + task1.priority);
        System.out.println("Progress: " + task1.progress + "%");
        
        Task task2 = new Task();

        task2.id = 1002L;
        task2.title = "Design Database";
        task2.description = "Design PostgreSQL database";
        task2.priority = "HIGH";
        task2.progress = 70;

        System.out.println();
        System.out.println("ID: " + task2.id);
        System.out.println("Task: " + task2.title);
        System.out.println("Description: " + task2.description);
        System.out.println("Priority: " + task2.priority);
        System.out.println("Progress: " + task2.progress + "%");
    }
}