package com.heibonsalaryman.bugtracker.repository;

import com.heibonsalaryman.bugtracker.model.ProjectMaster;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class ProjectMasterRepository {

    private static final RowMapper<ProjectMaster> ROW_MAPPER = (rs, rowNum) -> new ProjectMaster(
            rs.getString("project_no"),
            rs.getDate("release_planned_on").toLocalDate()
    );

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProjectMasterRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProjectMaster> findAll() {
        return jdbcTemplate.query("SELECT project_no, release_planned_on FROM project_master ORDER BY project_no", ROW_MAPPER);
    }

    public void insert(ProjectMaster projectMaster) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("projectNo", projectMaster.projectNo())
                .addValue("releasePlannedOn", Date.valueOf(projectMaster.releasePlannedOn()));
        jdbcTemplate.update("INSERT INTO project_master(project_no, release_planned_on) VALUES(:projectNo, :releasePlannedOn)", params);
    }
}
