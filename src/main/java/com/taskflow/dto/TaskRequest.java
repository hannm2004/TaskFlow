package com.taskflow.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TaskRequest {

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotBlank(message = "Priority must not be blank")
    private String priority;

    @NotBlank(message = "Status must not be blank")
    private String status;

    @Min(value = 0, message = "Progress must be at least 0")
    @Max(value = 100, message = "Progress must not exceed 100")
    private Integer progress;

    public TaskRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}