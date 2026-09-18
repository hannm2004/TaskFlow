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

        String status = getTaskStatus(progress);

        System.out.println("Status: " + status);
    }

    public static String getTaskStatus(int progress) {

        if (progress < 0 || progress > 100) {
            return "INVALID";
        }

        if (progress == 100) {
            return "DONE";
        }

        if (progress >= 50) {
            return "IN_PROGRESS";
        }

        return "TODO";
    }
}