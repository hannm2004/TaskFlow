/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taskflow;

/**
 *
 * @author HP
 */
public class FeatureTask extends Task implements Assignable {

    private String featureCategory;

    public FeatureTask(
            long id,
            String title,
            String description,
            String priority,
            int progress,
            String featureCategory
    ) {
        super(id, title, description, priority, progress);
        this.featureCategory = featureCategory;
    }

    public String getFeatureCategory() {
        return featureCategory;
    }

    @Override
    public String getTaskType() {
        return "FEATURE";
    }

    @Override
    public void assignTo(String username) {
        System.out.println(
                "Feature task \"" + getTitle()
                + "\" assigned to " + username
        );
    }
}
