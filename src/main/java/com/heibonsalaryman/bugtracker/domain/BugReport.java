package com.heibonsalaryman.bugtracker.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BugReport {
    private Long id;
    private String subject;
    private String systemName;
    private String reporter;
    private LocalDateTime reportedAt;
    private String projectName;
    private String testPhase;
    private String testCaseNumber;
    private String phenomenon;
    private String reproductionSteps;
    private LocalDate occurrenceDate;
    private String environment;
    private String location;
    private Reproducibility reproducibility;
    private Impact impact;
    private String cause;
    private String assignee;
    private LocalDateTime respondedAt;
    private String responseDetails;
    private String classification;
    private String injectionPhase;
    private String injectionVendor;
    private BugStatus status;

    public BugReport() {
    }

    public BugReport(Long id,
                     String subject,
                     String systemName,
                     String reporter,
                     LocalDateTime reportedAt,
                     String projectName,
                     String testPhase,
                     String testCaseNumber,
                     String phenomenon,
                     String reproductionSteps,
                     LocalDate occurrenceDate,
                     String environment,
                     String location,
                     Reproducibility reproducibility,
                     Impact impact,
                     String cause,
                     String assignee,
                     LocalDateTime respondedAt,
                     String responseDetails,
                     String classification,
                     String injectionPhase,
                     String injectionVendor,
                     BugStatus status) {
        this.id = id;
        this.subject = subject;
        this.systemName = systemName;
        this.reporter = reporter;
        this.reportedAt = reportedAt;
        this.projectName = projectName;
        this.testPhase = testPhase;
        this.testCaseNumber = testCaseNumber;
        this.phenomenon = phenomenon;
        this.reproductionSteps = reproductionSteps;
        this.occurrenceDate = occurrenceDate;
        this.environment = environment;
        this.location = location;
        this.reproducibility = reproducibility;
        this.impact = impact;
        this.cause = cause;
        this.assignee = assignee;
        this.respondedAt = respondedAt;
        this.responseDetails = responseDetails;
        this.classification = classification;
        this.injectionPhase = injectionPhase;
        this.injectionVendor = injectionVendor;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTestPhase() {
        return testPhase;
    }

    public void setTestPhase(String testPhase) {
        this.testPhase = testPhase;
    }

    public String getTestCaseNumber() {
        return testCaseNumber;
    }

    public void setTestCaseNumber(String testCaseNumber) {
        this.testCaseNumber = testCaseNumber;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    public String getReproductionSteps() {
        return reproductionSteps;
    }

    public void setReproductionSteps(String reproductionSteps) {
        this.reproductionSteps = reproductionSteps;
    }

    public LocalDate getOccurrenceDate() {
        return occurrenceDate;
    }

    public void setOccurrenceDate(LocalDate occurrenceDate) {
        this.occurrenceDate = occurrenceDate;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Reproducibility getReproducibility() {
        return reproducibility;
    }

    public void setReproducibility(Reproducibility reproducibility) {
        this.reproducibility = reproducibility;
    }

    public Impact getImpact() {
        return impact;
    }

    public void setImpact(Impact impact) {
        this.impact = impact;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public LocalDateTime getRespondedAt() {
        return respondedAt;
    }

    public void setRespondedAt(LocalDateTime respondedAt) {
        this.respondedAt = respondedAt;
    }

    public String getResponseDetails() {
        return responseDetails;
    }

    public void setResponseDetails(String responseDetails) {
        this.responseDetails = responseDetails;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getInjectionPhase() {
        return injectionPhase;
    }

    public void setInjectionPhase(String injectionPhase) {
        this.injectionPhase = injectionPhase;
    }

    public String getInjectionVendor() {
        return injectionVendor;
    }

    public void setInjectionVendor(String injectionVendor) {
        this.injectionVendor = injectionVendor;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }
}
