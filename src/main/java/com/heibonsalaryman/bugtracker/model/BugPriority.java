package com.heibonsalaryman.bugtracker.model;

public enum BugPriority {
    LOW("低"),
    MEDIUM("中"),
    HIGH("高"),
    CRITICAL("致命的");

    private final String displayName;

    BugPriority(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
