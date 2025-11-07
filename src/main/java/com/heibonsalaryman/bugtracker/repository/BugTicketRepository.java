package com.heibonsalaryman.bugtracker.repository;

import com.heibonsalaryman.bugtracker.model.BugPriority;
import com.heibonsalaryman.bugtracker.model.BugStatus;
import com.heibonsalaryman.bugtracker.model.BugTicket;
import com.heibonsalaryman.bugtracker.model.BugTicketRegistration;
import com.heibonsalaryman.bugtracker.model.MonthlyBugCount;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Repository
public class BugTicketRepository {

    private static final RowMapper<BugTicket> ROW_MAPPER = (rs, rowNum) -> new BugTicket(
            rs.getLong("bug_id"),
            rs.getString("title"),
            BugStatus.valueOf(rs.getString("status")),
            BugPriority.valueOf(rs.getString("priority")),
            rs.getDate("reported_on").toLocalDate(),
            rs.getString("system_code"),
            rs.getString("system_name"),
            rs.getString("project_no"),
            rs.getString("vendor_code"),
            rs.getString("vendor_name"),
            rs.getString("description")
    );

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BugTicketRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BugTicket> findAll() {
        String sql = """
                SELECT b.bug_id, b.title, b.status, b.priority, b.reported_on,
                       b.system_code, s.system_name,
                       b.project_no,
                       b.vendor_code, v.vendor_name,
                       b.description
                FROM bug_ticket b
                JOIN system_master s ON s.system_code = b.system_code
                JOIN vendor_master v ON v.vendor_code = b.vendor_code
                ORDER BY b.reported_on DESC, b.bug_id DESC
                """;
        return jdbcTemplate.query(sql, ROW_MAPPER);
    }

    public long insert(BugTicketRegistration registration) {
        String sql = "INSERT INTO bug_ticket(title, description, status, priority, system_code, project_no, vendor_code, reported_on) " +
                "VALUES(:title, :description, :status, :priority, :systemCode, :projectNo, :vendorCode, :reportedOn)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", registration.title())
                .addValue("description", registration.description())
                .addValue("status", registration.status().name())
                .addValue("priority", registration.priority().name())
                .addValue("systemCode", registration.systemCode())
                .addValue("projectNo", registration.projectNo())
                .addValue("vendorCode", registration.vendorCode())
                .addValue("reportedOn", Date.valueOf(registration.reportedOn()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder, new String[]{"bug_id"});
        Number key = keyHolder.getKey();
        return key != null ? key.longValue() : 0L;
    }

    public List<MonthlyBugCount> findMonthlyCountsFrom(LocalDate startDate) {
        String sql = """
                SELECT DATE_TRUNC('MONTH', reported_on) AS month_start,
                       status,
                       COUNT(*) AS bug_count
                FROM bug_ticket
                WHERE reported_on >= :startDate
                GROUP BY DATE_TRUNC('MONTH', reported_on), status
                ORDER BY month_start, status
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("startDate", Date.valueOf(startDate));
        return jdbcTemplate.query(sql, params, (rs, rowNum) -> new MonthlyBugCount(
                YearMonth.from(rs.getDate("month_start").toLocalDate()),
                BugStatus.valueOf(rs.getString("status")),
                rs.getLong("bug_count")
        ));
    }
}
