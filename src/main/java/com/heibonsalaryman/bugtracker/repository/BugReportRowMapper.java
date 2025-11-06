package com.heibonsalaryman.bugtracker.repository;

import com.heibonsalaryman.bugtracker.domain.BugReport;
import com.heibonsalaryman.bugtracker.domain.BugStatus;
import com.heibonsalaryman.bugtracker.domain.Impact;
import com.heibonsalaryman.bugtracker.domain.Reproducibility;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.RowMapper;

class BugReportRowMapper implements RowMapper<BugReport> {

    @Override
    public BugReport mapRow(ResultSet rs, int rowNum) throws SQLException {
        BugReport bugReport = new BugReport();
        bugReport.setId(rs.getLong("id"));
        bugReport.setSubject(rs.getString("subject"));
        bugReport.setSystemName(rs.getString("system_name"));
        bugReport.setReporter(rs.getString("reporter"));
        bugReport.setReportedAt(getLocalDateTime(rs, "reported_at"));
        bugReport.setProjectName(rs.getString("project_name"));
        bugReport.setTestPhase(rs.getString("test_phase"));
        bugReport.setTestCaseNumber(rs.getString("test_case_number"));
        bugReport.setPhenomenon(rs.getString("phenomenon"));
        bugReport.setReproductionSteps(rs.getString("reproduction_steps"));
        bugReport.setOccurrenceDate(getLocalDate(rs, "occurrence_date"));
        bugReport.setEnvironment(rs.getString("environment"));
        bugReport.setLocation(rs.getString("location"));
        bugReport.setReproducibility(getEnum(rs.getString("reproducibility"), Reproducibility.class));
        bugReport.setImpact(getEnum(rs.getString("impact"), Impact.class));
        bugReport.setCause(rs.getString("cause"));
        bugReport.setAssignee(rs.getString("assignee"));
        bugReport.setRespondedAt(getLocalDateTime(rs, "responded_at"));
        bugReport.setResponseDetails(rs.getString("response_details"));
        bugReport.setClassification(rs.getString("classification"));
        bugReport.setInjectionPhase(rs.getString("injection_phase"));
        bugReport.setInjectionVendor(rs.getString("injection_vendor"));
        bugReport.setStatus(getEnum(rs.getString("status"), BugStatus.class));
        return bugReport;
    }

    private LocalDate getLocalDate(ResultSet rs, String column) throws SQLException {
        java.sql.Date date = rs.getDate(column);
        return date != null ? date.toLocalDate() : null;
    }

    private LocalDateTime getLocalDateTime(ResultSet rs, String column) throws SQLException {
        java.sql.Timestamp timestamp = rs.getTimestamp(column);
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }

    private <T extends Enum<T>> T getEnum(String value, Class<T> enumType) {
        if (value == null) {
            return null;
        }
        return Enum.valueOf(enumType, value);
    }
}
