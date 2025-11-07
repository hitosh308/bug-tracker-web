CREATE TABLE bug_ticket (
    bug_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(20) NOT NULL,
    priority VARCHAR(20) NOT NULL,
    system_code VARCHAR(20) NOT NULL,
    project_no VARCHAR(30) NOT NULL,
    vendor_code VARCHAR(20) NOT NULL,
    reported_on DATE NOT NULL,
    CONSTRAINT fk_bug_ticket_system FOREIGN KEY (system_code) REFERENCES system_master(system_code),
    CONSTRAINT fk_bug_ticket_project FOREIGN KEY (project_no) REFERENCES project_master(project_no),
    CONSTRAINT fk_bug_ticket_vendor FOREIGN KEY (vendor_code) REFERENCES vendor_master(vendor_code)
);

INSERT INTO bug_ticket(title, description, status, priority, system_code, project_no, vendor_code, reported_on) VALUES
    ('ログイン画面でエラー', '特定条件でログイン画面が500エラーになる', 'NEW', 'HIGH', 'CORE', 'PRJ-001', 'VEN001', DATEADD('DAY', -3, CURRENT_DATE())),
    ('レポート出力の遅延', 'レポート出力に5分以上かかるケースがある', 'IN_PROGRESS', 'MEDIUM', 'QA', 'PRJ-002', 'VEN002', DATEADD('DAY', -10, CURRENT_DATE()));
