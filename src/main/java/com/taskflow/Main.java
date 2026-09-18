package com.taskflow;

public class Main {

    public static void main(String[] args) {

        int[] progresses = {100, 70, 30, 0};

        System.out.println("=== TASKFLOW TASKS ===");

        for (int i = 0; i < progresses.length; i++) {

            int progress = progresses[i];

            String status = getTaskStatus(progress);

            System.out.println(
                    "Task " + (i + 1)
                            + " | Progress: " + progress + "%"
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