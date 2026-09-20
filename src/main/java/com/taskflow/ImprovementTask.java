package com.taskflow;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class ImprovementTask extends Task {

    private String improvementArea;

    public ImprovementTask(
            long id,
            String title,
            String description,
            String priority,
            int progress,
            String improvementArea
    ) {
        super(id, title, description, priority, progress);
        this.improvementArea = improvementArea;
    }

    public String getImprovementArea() {
        return improvementArea;
    }

    @Override
    public String getTaskType() {
        return "IMPROVEMENT";
    }
}
