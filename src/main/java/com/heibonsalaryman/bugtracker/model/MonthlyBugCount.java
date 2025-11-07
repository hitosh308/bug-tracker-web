package com.heibonsalaryman.bugtracker.model;

import java.time.YearMonth;

public record MonthlyBugCount(
        YearMonth month,
        BugStatus status,
        long count
) {
}
