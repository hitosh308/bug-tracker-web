package com.heibonsalaryman.bugtracker.repository;

import com.heibonsalaryman.bugtracker.model.SystemMaster;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SystemMasterRepository {

    private static final RowMapper<SystemMaster> ROW_MAPPER = (rs, rowNum) -> new SystemMaster(
            rs.getString("system_code"),
            rs.getString("system_name")
    );

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SystemMasterRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SystemMaster> findAll() {
        return jdbcTemplate.query("SELECT system_code, system_name FROM system_master ORDER BY system_code", ROW_MAPPER);
    }

    public void insert(SystemMaster systemMaster) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("systemCode", systemMaster.systemCode())
                .addValue("systemName", systemMaster.systemName());
        jdbcTemplate.update("INSERT INTO system_master(system_code, system_name) VALUES(:systemCode, :systemName)", params);
    }
}
