package com.heibonsalaryman.bugtracker.controller;

import com.heibonsalaryman.bugtracker.controller.form.BugReportForm;
import com.heibonsalaryman.bugtracker.domain.BugStatus;
import com.heibonsalaryman.bugtracker.domain.Impact;
import com.heibonsalaryman.bugtracker.domain.Reproducibility;
import com.heibonsalaryman.bugtracker.service.BugReportService;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BugReportController {

    private final BugReportService service;

    public BugReportController(BugReportService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/bugs";
    }

    @GetMapping("/bugs")
    public String list(Model model) {
        model.addAttribute("bugReports", service.findAll());
        return "bug/list";
    }

    @GetMapping("/bugs/new")
    public String createForm(Model model) {
        if (!model.containsAttribute("bugReportForm")) {
            model.addAttribute("bugReportForm", new BugReportForm());
        }
        return "bug/form";
    }

    @PostMapping("/bugs")
    public String create(@Valid @ModelAttribute("bugReportForm") BugReportForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bugReportForm", bindingResult);
            redirectAttributes.addFlashAttribute("bugReportForm", form);
            return "redirect:/bugs/new";
        }

        service.create(form.toEntity());
        redirectAttributes.addFlashAttribute("message", "バグ情報を登録しました");
        return "redirect:/bugs";
    }

    @ModelAttribute("reproducibilityOptions")
    public List<Reproducibility> reproducibilityOptions() {
        return Arrays.asList(Reproducibility.values());
    }

    @ModelAttribute("impactOptions")
    public List<Impact> impactOptions() {
        return Arrays.asList(Impact.values());
    }

    @ModelAttribute("statusOptions")
    public List<BugStatus> statusOptions() {
        return Arrays.asList(BugStatus.values());
    }
}
