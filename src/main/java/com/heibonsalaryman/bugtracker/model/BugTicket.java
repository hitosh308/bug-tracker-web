package com.heibonsalaryman.bugtracker.model;

import java.time.LocalDate;

public record BugTicket(
        Long bugId,
        String title,
        BugStatus status,
        BugPriority priority,
        LocalDate reportedOn,
        String systemCode,
        String systemName,
        String projectNo,
        String vendorCode,
        String vendorName,
        String description
) {
}
