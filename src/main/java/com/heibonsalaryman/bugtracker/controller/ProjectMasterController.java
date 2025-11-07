package com.heibonsalaryman.bugtracker.controller;

import com.heibonsalaryman.bugtracker.model.ProjectMaster;
import com.heibonsalaryman.bugtracker.service.ProjectMasterService;
import com.heibonsalaryman.bugtracker.web.form.ProjectMasterForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.YearMonth;

@Controller
@RequestMapping("/masters/projects")
public class ProjectMasterController {

    private final ProjectMasterService service;

    public ProjectMasterController(ProjectMasterService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("projects", service.findAll());
        return "masters/projects/list";
    }

    @GetMapping("/new")
    public String showForm(@ModelAttribute("projectMasterForm") ProjectMasterForm form) {
        return "masters/projects/form";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("projectMasterForm") ProjectMasterForm form,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "masters/projects/form";
        }

        YearMonth yearMonth = YearMonth.parse(form.getReleasePlanYm());
        service.register(new ProjectMaster(form.getProjectNo(), yearMonth.atDay(1)));
        redirectAttributes.addFlashAttribute("message", "プロジェクトマスタを登録しました");
        return "redirect:/masters/projects";
    }
}
