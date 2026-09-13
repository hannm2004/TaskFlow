package com.taskflow;

public class Task {

    private String title;
    private TaskPriority priority;
    private int progress;

    public Task(String title, TaskPriority priority, int progress) {
        this.title = title;
        this.priority = priority;
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public int getProgress() {
        return progress;
    }

    public String getStatus() {
        return TaskUtils.getTaskStatus(progress);
    }
}