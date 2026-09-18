package com.taskflow;

public class Main {

    public static void main(String[] args) {

        long taskId = 1001L;

        String taskTitle = "Implement Docker";
        String taskDescription = "Learn Docker and containerize TaskFlow";

        String priority = "HIGH";

        int progress = 30;

        boolean completed = false;

        System.out.println("=== TASKFLOW ===");
        System.out.println("ID: " + taskId);
        System.out.println("Task: " + taskTitle);
        System.out.println("Description: " + taskDescription);
        System.out.println("Priority: " + priority);
        System.out.println("Progress: " + progress + "%");
        System.out.println("Completed: " + completed);
    }
}