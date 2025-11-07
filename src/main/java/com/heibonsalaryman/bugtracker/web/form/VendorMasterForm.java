package com.heibonsalaryman.bugtracker.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class VendorMasterForm {

    @NotBlank(message = "ベンダーコードを入力してください")
    @Pattern(regexp = "^[A-Z0-9_-]+$", message = "ベンダーコードは英数字・ハイフン・アンダースコアで入力してください")
    @Size(max = 20, message = "ベンダーコードは20文字以内で入力してください")
    private String vendorCode;

    @NotBlank(message = "ベンダー名称を入力してください")
    @Size(max = 100, message = "ベンダー名称は100文字以内で入力してください")
    private String vendorName;

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
