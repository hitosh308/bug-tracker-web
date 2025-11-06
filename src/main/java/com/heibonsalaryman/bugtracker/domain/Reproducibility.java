package com.heibonsalaryman.bugtracker.domain;

public enum Reproducibility {
    REPRODUCIBLE("あり"),
    NON_REPRODUCIBLE("なし");

    private final String label;

    Reproducibility(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
