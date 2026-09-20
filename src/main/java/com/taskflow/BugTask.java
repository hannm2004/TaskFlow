/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taskflow;

/**
 *
 * @author HP
 */
public class BugTask extends Task implements Assignable {

    private String bugSeverity;

    public BugTask(
            long id,
            String title,
            String description,
            String priority,
            int progress,
            String bugSeverity
    ) {
        super(id, title, description, priority, progress);
        this.bugSeverity = bugSeverity;
    }

    public String getBugSeverity() {
        return bugSeverity;
    }

    @Override
    public String getTaskType() {
        return "BUG";
    }

    @Override
    public void assignTo(String username) {
        System.out.println(
                "Bug task \"" + getTitle()
                + "\" assigned to " + username
        );
    }
}
