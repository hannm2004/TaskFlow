/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taskflow;

/**
 *
 * @author HP
 */
public class Task {

    private long id;
    private String title;
    private String description;
    private String priority;
    private int progress;

    public Task(
            long id,
            String title,
            String description,
            String priority,
            int progress
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        setProgress(progress);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException(
                    "Progress must be between 0 and 100"
            );
        }

        this.progress = progress;
    }
    
    public String getTaskType() {
        return "GENERAL";
    }

    String getBugSeverity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
