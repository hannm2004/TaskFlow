package com.taskflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response body returned when reading task data")
public class TaskResponse {

    @Schema(description = "Unique identifier of the task", example = "1")
    private Long id;

    @Schema(description = "Title of the task", example = "Fix login bug")
    private String title;

    @Schema(description = "Detailed description of the task", example = "Users cannot log in with special characters in password")
    private String description;

    @Schema(description = "Priority level of the task", example = "HIGH")
    private String priority;

    @Schema(description = "Current status of the task", example = "TODO")
    private String status;

    @Schema(description = "Completion progress percentage (0–100)", example = "0")
    private Integer progress;

    public TaskResponse() {
    }

    public TaskResponse(
            Long id,
            String title,
            String description,
            String priority,
            String status,
            Integer progress) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.progress = progress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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