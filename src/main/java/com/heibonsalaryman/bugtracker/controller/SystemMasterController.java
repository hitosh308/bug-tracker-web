package com.heibonsalaryman.bugtracker.controller;

import com.heibonsalaryman.bugtracker.model.SystemMaster;
import com.heibonsalaryman.bugtracker.service.SystemMasterService;
import com.heibonsalaryman.bugtracker.web.form.SystemMasterForm;
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
@RequestMapping("/masters/systems")
public class SystemMasterController {

    private final SystemMasterService service;

    public SystemMasterController(SystemMasterService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("systems", service.findAll());
        return "masters/systems/list";
    }

    @GetMapping("/new")
    public String showForm(@ModelAttribute("systemMasterForm") SystemMasterForm form) {
        return "masters/systems/form";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("systemMasterForm") SystemMasterForm form,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "masters/systems/form";
        }

        service.register(new SystemMaster(form.getSystemCode(), form.getSystemName()));
        redirectAttributes.addFlashAttribute("message", "システムマスタを登録しました");
        return "redirect:/masters/systems";
    }
}
