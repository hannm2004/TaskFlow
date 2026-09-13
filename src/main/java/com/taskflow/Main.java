package com.taskflow;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("        TASKFLOW PROJECT");
        System.out.println("================================");
        System.out.println("Hello, TaskFlow!");

        List<Task> tasks = new ArrayList<>();

        tasks.add(new Task(
                "Implement Login",
                TaskPriority.HIGH,
                100
        ));

        tasks.add(new Task(
                "Design Database",
                TaskPriority.HIGH,
                70
        ));

        tasks.add(new Task(
                "Create REST API",
                TaskPriority.MEDIUM,
                30
        ));

        tasks.add(new Task(
                "Implement Docker",
                TaskPriority.URGENT,
                0
        ));

        System.out.println("\nTask List:");

        for (Task task : tasks) {
            System.out.println(
                    task.getTitle()
                            + " | "
                            + task.getPriority()
                            + " | "
                            + task.getProgress()
                            + "% | "
                            + task.getStatus()
            );
        }
    }
}