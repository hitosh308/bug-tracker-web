package com.heibonsalaryman.bugtracker.web.form;

import com.heibonsalaryman.bugtracker.model.BugPriority;
import com.heibonsalaryman.bugtracker.model.BugStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class BugTicketForm {

    @NotBlank(message = "タイトルを入力してください")
    @Size(max = 100, message = "タイトルは100文字以内で入力してください")
    private String title;

    @NotBlank(message = "詳細を入力してください")
    @Size(max = 2000, message = "詳細は2000文字以内で入力してください")
    private String description;

    @NotNull(message = "ステータスを選択してください")
    private BugStatus status;

    @NotNull(message = "優先度を選択してください")
    private BugPriority priority;

    @NotNull(message = "発生日を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportedOn;

    @NotBlank(message = "システムを選択してください")
    private String systemCode;

    @NotBlank(message = "プロジェクトを選択してください")
    private String projectNo;

    @NotBlank(message = "ベンダーを選択してください")
    private String vendorCode;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public BugPriority getPriority() {
        return priority;
    }

    public void setPriority(BugPriority priority) {
        this.priority = priority;
    }

    public LocalDate getReportedOn() {
        return reportedOn;
    }

    public void setReportedOn(LocalDate reportedOn) {
        this.reportedOn = reportedOn;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
}
