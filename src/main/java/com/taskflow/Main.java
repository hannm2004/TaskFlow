package com.taskflow;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("        TASKFLOW PROJECT");
        System.out.println("================================");
        System.out.println("Hello, TaskFlow!");

        tasks.add(new Task(
                "Implement Login",
                "HIGH",
                100
        ));

        tasks.add(new Task(
                "Design Database",
                "HIGH",
                70
        ));

        tasks.add(new Task(
                "Create REST API",
                "MEDIUM",
                30
        ));

        tasks.add(new Task(
                "Implement Docker",
                "URGENT",
                0
        ));

        //String status = TaskUtils.getTaskStatus(progress);

        System.out.println("Task: " + task.getTitle());
        System.out.println("Priority: " + task.getPriority());
        System.out.println("Progress: " + task.getProgress() + "%");
        System.out.println("Completed: " + completed);
        System.out.println("Status: " + task.getStatus());

        String[] tasks = {
                "Implement Login",
                "Design Database",
                "Create REST API",
                "Implement Docker",
                "Write Tests"
        };
        for (int i = 0; i < tasks.length; i++){
            System.out.println("Task " + (i+1) + ": " + tasks[i]);
        }

        int[] progresses = {100, 70, 40, 30, 0};
        for (int i = 0; i < progresses.length; i++)
        {
            System.out.println(tasks[i] + " - " + TaskUtils.getTaskStatus(progresses[i]));
        }
    }
}
