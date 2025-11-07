package com.heibonsalaryman.bugtracker.model;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public record BugTrendSummary(
        List<YearMonth> months,
        Map<BugStatus, List<Long>> statusCounts
) {
}
