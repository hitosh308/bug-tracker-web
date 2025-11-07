package com.heibonsalaryman.bugtracker.service;

import com.heibonsalaryman.bugtracker.domain.BugReport;
import com.heibonsalaryman.bugtracker.domain.BugStatus;
import com.heibonsalaryman.bugtracker.repository.BugReportRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class BugReportService {

    private final BugReportRepository repository;

    public BugReportService(BugReportRepository repository) {
        this.repository = repository;
    }

    public List<BugReport> findAll() {
        return repository.findAll();
    }

    @Transactional
    public BugReport create(BugReport bugReport) {
        if (bugReport.getReportedAt() == null) {
            bugReport.setReportedAt(LocalDateTime.now());
        }
        if (!StringUtils.hasText(bugReport.getReporter())) {
            bugReport.setReporter("匿名ユーザー");
        }
        if (bugReport.getStatus() == null) {
            bugReport.setStatus(BugStatus.UNRESOLVED);
        }
        return repository.save(bugReport);
    }
}
