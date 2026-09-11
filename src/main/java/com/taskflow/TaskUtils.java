package com.taskflow;

public class TaskUtils {
    public static String getTaskStatus(int progress) {
        if (progress == 100) {
            return "DONE";
        } else if (progress >= 50) {
            return "IN_PROGRESS";
        }
        return "TODO";
    }
}
