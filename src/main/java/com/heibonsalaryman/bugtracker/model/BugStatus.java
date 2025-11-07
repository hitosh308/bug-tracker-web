package com.heibonsalaryman.bugtracker.model;

public enum BugStatus {
    NEW("新規"),
    IN_PROGRESS("対応中"),
    RESOLVED("解決"),
    CLOSED("クローズ");

    private final String displayName;

    BugStatus(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
