package com.heibonsalaryman.bugtracker.controller.form;

import com.heibonsalaryman.bugtracker.domain.BugReport;
import com.heibonsalaryman.bugtracker.domain.BugStatus;
import com.heibonsalaryman.bugtracker.domain.Impact;
import com.heibonsalaryman.bugtracker.domain.Reproducibility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class BugReportForm {

    @NotBlank(message = "件名は必須です")
    private String subject;

    @NotBlank(message = "発生システムは必須です")
    private String systemName;

    private String reporter;

    @NotBlank(message = "発生プロジェクトは必須です")
    private String projectName;

    @NotBlank(message = "発生テスト工程は必須です")
    private String testPhase;

    private String testCaseNumber;

    @NotBlank(message = "発生事象は必須です")
    private String phenomenon;

    private String reproductionSteps;

    @NotNull(message = "発生日は必須です")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate occurrenceDate;

    private String environment;

    private String location;

    @NotNull(message = "再現性は必須です")
    private Reproducibility reproducibility;

    @NotNull(message = "影響度は必須です")
    private Impact impact;

    private String cause;

    private String assignee;

    private String responseDetails;

    private String classification;

    private String injectionPhase;

    private String injectionVendor;

    private BugStatus status;

    public BugReport toEntity() {
        BugReport bugReport = new BugReport();
        bugReport.setSubject(subject);
        bugReport.setSystemName(systemName);
        bugReport.setReporter(reporter);
        bugReport.setProjectName(projectName);
        bugReport.setTestPhase(testPhase);
        bugReport.setTestCaseNumber(testCaseNumber);
        bugReport.setPhenomenon(phenomenon);
        bugReport.setReproductionSteps(reproductionSteps);
        bugReport.setOccurrenceDate(occurrenceDate);
        bugReport.setEnvironment(environment);
        bugReport.setLocation(location);
        bugReport.setReproducibility(reproducibility);
        bugReport.setImpact(impact);
        bugReport.setCause(cause);
        bugReport.setAssignee(assignee);
        bugReport.setResponseDetails(responseDetails);
        bugReport.setClassification(classification);
        bugReport.setInjectionPhase(injectionPhase);
        bugReport.setInjectionVendor(injectionVendor);
        bugReport.setStatus(status);
        return bugReport;
    }

    // getters and setters

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
