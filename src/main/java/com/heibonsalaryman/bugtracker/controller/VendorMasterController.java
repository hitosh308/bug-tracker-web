package com.heibonsalaryman.bugtracker.controller;

import com.heibonsalaryman.bugtracker.model.VendorMaster;
import com.heibonsalaryman.bugtracker.service.VendorMasterService;
import com.heibonsalaryman.bugtracker.web.form.VendorMasterForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/masters/vendors")
public class VendorMasterController {

    private final VendorMasterService service;

    public VendorMasterController(VendorMasterService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("vendors", service.findAll());
        return "masters/vendors/list";
    }

    @GetMapping("/new")
    public String showForm(@ModelAttribute("vendorMasterForm") VendorMasterForm form) {
        return "masters/vendors/form";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("vendorMasterForm") VendorMasterForm form,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "masters/vendors/form";
        }

        service.register(new VendorMaster(form.getVendorCode(), form.getVendorName()));
        redirectAttributes.addFlashAttribute("message", "ベンダーマスタを登録しました");
        return "redirect:/masters/vendors";
    }
}
