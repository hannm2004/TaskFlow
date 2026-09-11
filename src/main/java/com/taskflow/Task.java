package com.taskflow;

public class Task {
    private String title;
    private String priority;
    private int progress;

    public Task(String title, String priority, int progress){
        this.title = title;
        this.priority = priority;
        this.progress = progress;
    }

    public String getTitle(){
        return title;
    }

    public String getPriority(){
        return priority;
    }

    public int getProgress(){
        return progress;
    }

    public String getStatus(){
        return TaskUtils.getTaskStatus(progress);
    }
}
