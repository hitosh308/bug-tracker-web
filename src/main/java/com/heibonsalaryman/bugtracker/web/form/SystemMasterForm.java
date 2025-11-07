package com.heibonsalaryman.bugtracker.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SystemMasterForm {

    @NotBlank(message = "システムコードを入力してください")
    @Pattern(regexp = "^[A-Z0-9_-]+$", message = "システムコードは英数字・ハイフン・アンダースコアで入力してください")
    @Size(max = 20, message = "システムコードは20文字以内で入力してください")
    private String systemCode;

    @NotBlank(message = "システム名称を入力してください")
    @Size(max = 100, message = "システム名称は100文字以内で入力してください")
    private String systemName;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
}
