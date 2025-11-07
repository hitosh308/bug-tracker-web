CREATE TABLE system_master (
    system_code VARCHAR(20) PRIMARY KEY,
    system_name VARCHAR(100) NOT NULL
);

CREATE TABLE project_master (
    project_no VARCHAR(30) PRIMARY KEY,
    release_planned_on DATE NOT NULL
);

CREATE TABLE vendor_master (
    vendor_code VARCHAR(20) PRIMARY KEY,
    vendor_name VARCHAR(100) NOT NULL
);

INSERT INTO system_master(system_code, system_name) VALUES
    ('CORE', 'コア業務システム'),
    ('QA', '品質管理システム');

INSERT INTO project_master(project_no, release_planned_on) VALUES
    ('PRJ-001', DATE '2024-06-01'),
    ('PRJ-002', DATE '2024-09-01');

INSERT INTO vendor_master(vendor_code, vendor_name) VALUES
    ('VEN001', '株式会社テスト'),
    ('VEN002', '有限会社サンプル');
