package com.heibonsalaryman.bugtracker.model;

import java.time.LocalDate;

public record ProjectMaster(
        String projectNo,
        LocalDate releasePlannedOn
) {
}
