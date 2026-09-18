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

    long id;
    String title;
    String description;
    String priority;
    int progress;

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
        this.progress = progress;
    }
}
