package com.heibonsalaryman.bugtracker.repository;

import com.heibonsalaryman.bugtracker.model.VendorMaster;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendorMasterRepository {

    private static final RowMapper<VendorMaster> ROW_MAPPER = (rs, rowNum) -> new VendorMaster(
            rs.getString("vendor_code"),
            rs.getString("vendor_name")
    );

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public VendorMasterRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<VendorMaster> findAll() {
        return jdbcTemplate.query("SELECT vendor_code, vendor_name FROM vendor_master ORDER BY vendor_code", ROW_MAPPER);
    }

    public void insert(VendorMaster vendorMaster) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("vendorCode", vendorMaster.vendorCode())
                .addValue("vendorName", vendorMaster.vendorName());
        jdbcTemplate.update("INSERT INTO vendor_master(vendor_code, vendor_name) VALUES(:vendorCode, :vendorName)", params);
    }
}
