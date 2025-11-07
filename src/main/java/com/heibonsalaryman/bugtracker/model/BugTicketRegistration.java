package com.heibonsalaryman.bugtracker.model;

import java.time.LocalDate;

public record BugTicketRegistration(
        String title,
        String description,
        BugStatus status,
        BugPriority priority,
        LocalDate reportedOn,
        String systemCode,
        String projectNo,
        String vendorCode
) {
}
