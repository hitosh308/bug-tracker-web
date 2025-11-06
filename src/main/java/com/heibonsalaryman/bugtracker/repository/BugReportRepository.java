package com.heibonsalaryman.bugtracker.repository;

import com.heibonsalaryman.bugtracker.domain.BugReport;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class BugReportRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insert;

    public BugReportRepository(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource)
                .withTableName("bug_report")
                .usingGeneratedKeyColumns("id");
    }

    public List<BugReport> findAll() {
        String sql = "SELECT * FROM bug_report ORDER BY reported_at DESC NULLS LAST, id DESC";
        return jdbcTemplate.query(sql, new BugReportRowMapper());
    }

    public BugReport save(BugReport bugReport) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("subject", bugReport.getSubject())
                .addValue("system_name", bugReport.getSystemName())
                .addValue("reporter", bugReport.getReporter())
                .addValue("reported_at", toTimestamp(bugReport.getReportedAt()))
                .addValue("project_name", bugReport.getProjectName())
                .addValue("test_phase", bugReport.getTestPhase())
                .addValue("test_case_number", bugReport.getTestCaseNumber())
                .addValue("phenomenon", bugReport.getPhenomenon())
                .addValue("reproduction_steps", bugReport.getReproductionSteps())
                .addValue("occurrence_date", toSqlDate(bugReport.getOccurrenceDate()))
                .addValue("environment", bugReport.getEnvironment())
                .addValue("location", bugReport.getLocation())
                .addValue("reproducibility", toString(bugReport.getReproducibility()))
                .addValue("impact", toString(bugReport.getImpact()))
                .addValue("cause", bugReport.getCause())
                .addValue("assignee", bugReport.getAssignee())
                .addValue("responded_at", toTimestamp(bugReport.getRespondedAt()))
                .addValue("response_details", bugReport.getResponseDetails())
                .addValue("classification", bugReport.getClassification())
                .addValue("injection_phase", bugReport.getInjectionPhase())
                .addValue("injection_vendor", bugReport.getInjectionVendor())
                .addValue("status", toString(bugReport.getStatus()));

        if (bugReport.getId() == null) {
            Number generatedId = insert.executeAndReturnKey(params);
            bugReport.setId(generatedId.longValue());
        } else {
            params.addValue("id", bugReport.getId());
            String sql = "UPDATE bug_report SET "
                    + "subject=:subject, system_name=:system_name, reporter=:reporter, reported_at=:reported_at, "
                    + "project_name=:project_name, test_phase=:test_phase, test_case_number=:test_case_number, "
                    + "phenomenon=:phenomenon, reproduction_steps=:reproduction_steps, occurrence_date=:occurrence_date, "
                    + "environment=:environment, location=:location, reproducibility=:reproducibility, impact=:impact, "
                    + "cause=:cause, assignee=:assignee, responded_at=:responded_at, response_details=:response_details, "
                    + "classification=:classification, injection_phase=:injection_phase, injection_vendor=:injection_vendor, "
                    + "status=:status WHERE id=:id";
            jdbcTemplate.update(sql, params);
        }
        return bugReport;
    }

    private java.sql.Date toSqlDate(LocalDate value) {
        return value != null ? java.sql.Date.valueOf(value) : null;
    }

    private Timestamp toTimestamp(LocalDateTime value) {
        return value != null ? Timestamp.valueOf(value) : null;
    }

    private String toString(Enum<?> value) {
        return value != null ? value.name() : null;
    }
}
