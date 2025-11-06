package com.heibonsalaryman.bugtracker.domain;

public enum Impact {
    HIGH("高"),
    MEDIUM("中"),
    LOW("低");

    private final String label;

    Impact(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
