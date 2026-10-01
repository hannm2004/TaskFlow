package com.taskflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request body for creating or updating a task")
public class TaskRequest {

    @Schema(description = "Title of the task", example = "Fix login bug", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Title must not be blank")
    private String title;

    @Schema(description = "Detailed description of the task", example = "Users cannot log in with special characters in password", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Description must not be blank")
    private String description;

    @Schema(description = "Priority level of the task (e.g., LOW, MEDIUM, HIGH)", example = "HIGH", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Priority must not be blank")
    private String priority;

    @Schema(description = "Current status of the task (e.g., TODO, IN_PROGRESS, DONE)", example = "TODO", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Status must not be blank")
    private String status;

    @Schema(description = "Completion progress percentage (0–100)", example = "0", minimum = "0", maximum = "100")
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