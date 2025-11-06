CREATE TABLE bug_report (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    subject VARCHAR(255) NOT NULL,
    system_name VARCHAR(255) NOT NULL,
    reporter VARCHAR(255) NOT NULL,
    reported_at TIMESTAMP,
    project_name VARCHAR(255) NOT NULL,
    test_phase VARCHAR(255) NOT NULL,
    test_case_number VARCHAR(255),
    phenomenon TEXT NOT NULL,
    reproduction_steps TEXT,
    occurrence_date DATE NOT NULL,
    environment VARCHAR(255),
    location VARCHAR(255),
    reproducibility VARCHAR(32) NOT NULL,
    impact VARCHAR(32) NOT NULL,
    cause VARCHAR(255),
    assignee VARCHAR(255),
    responded_at TIMESTAMP,
    response_details TEXT,
    classification VARCHAR(255),
    injection_phase VARCHAR(255),
    injection_vendor VARCHAR(255),
    status VARCHAR(32)
);

CREATE INDEX idx_bug_report_status ON bug_report(status);
CREATE INDEX idx_bug_report_project ON bug_report(project_name);
