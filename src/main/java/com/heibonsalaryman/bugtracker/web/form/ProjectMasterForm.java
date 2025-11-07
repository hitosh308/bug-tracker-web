package com.heibonsalaryman.bugtracker.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProjectMasterForm {

    @NotBlank(message = "プロジェクト番号を入力してください")
    @Pattern(regexp = "^[A-Z0-9_-]+$", message = "プロジェクト番号は英数字・ハイフン・アンダースコアで入力してください")
    @Size(max = 30, message = "プロジェクト番号は30文字以内で入力してください")
    private String projectNo;

    @NotBlank(message = "リリース予定年月を入力してください")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])$", message = "リリース予定年月はYYYY-MM形式で入力してください")
    private String releasePlanYm;

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getReleasePlanYm() {
        return releasePlanYm;
    }

    public void setReleasePlanYm(String releasePlanYm) {
        this.releasePlanYm = releasePlanYm;
    }
}
