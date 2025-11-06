package com.heibonsalaryman.bugtracker.domain;

public enum BugStatus {
    UNRESOLVED("未対応"),
    IN_PROGRESS("対応中"),
    RESOLVED("対応完了");

    private final String label;

    BugStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
