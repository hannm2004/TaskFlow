package com.taskflow;

public class Main {

    public static void main(String[] args) {

        long[] taskIds = {1001L, 1002L, 1003L, 1004L};

        String[] taskTitles = {
                "Implement Login",
                "Design Database",
                "Create REST API",
                "Implement Docker"
        };

        String[] priorities = {
                "HIGH",
                "HIGH",
                "MEDIUM",
                "URGENT"
        };

        int[] progresses = {100, 70, 30, 0};

        System.out.println("=== TASKFLOW TASKS ===");

        for (int i = 0; i < taskIds.length; i++) {

            String status = getTaskStatus(progresses[i]);

            System.out.println(
                    "ID: " + taskIds[i]
                            + " | Task: " + taskTitles[i]
                            + " | Priority: " + priorities[i]
                            + " | Progress: " + progresses[i] + "%"
                            + " | Status: " + status
            );
        }
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