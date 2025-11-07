package com.heibonsalaryman.bugtracker.controller;

import com.heibonsalaryman.bugtracker.model.BugPriority;
import com.heibonsalaryman.bugtracker.model.BugStatus;
import com.heibonsalaryman.bugtracker.model.BugTicketRegistration;
import com.heibonsalaryman.bugtracker.model.BugTrendSummary;
import com.heibonsalaryman.bugtracker.model.ProjectMaster;
import com.heibonsalaryman.bugtracker.model.SystemMaster;
import com.heibonsalaryman.bugtracker.model.VendorMaster;
import com.heibonsalaryman.bugtracker.service.BugTicketService;
import com.heibonsalaryman.bugtracker.service.ProjectMasterService;
import com.heibonsalaryman.bugtracker.service.SystemMasterService;
import com.heibonsalaryman.bugtracker.service.VendorMasterService;
import com.heibonsalaryman.bugtracker.web.form.BugTicketForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bugs")
public class BugTicketController {

    private final BugTicketService bugTicketService;
    private final SystemMasterService systemMasterService;
    private final ProjectMasterService projectMasterService;
    private final VendorMasterService vendorMasterService;

    public BugTicketController(BugTicketService bugTicketService,
                               SystemMasterService systemMasterService,
                               ProjectMasterService projectMasterService,
                               VendorMasterService vendorMasterService) {
        this.bugTicketService = bugTicketService;
        this.systemMasterService = systemMasterService;
        this.projectMasterService = projectMasterService;
        this.vendorMasterService = vendorMasterService;
    }

    @ModelAttribute("statuses")
    public BugStatus[] statuses() {
        return BugStatus.values();
    }

    @ModelAttribute("priorities")
    public BugPriority[] priorities() {
        return BugPriority.values();
    }

    @ModelAttribute("systems")
    public List<SystemMaster> systems() {
        return systemMasterService.findAll();
    }

    @ModelAttribute("projects")
    public List<ProjectMaster> projects() {
        return projectMasterService.findAll();
    }

    @ModelAttribute("vendors")
    public List<VendorMaster> vendors() {
        return vendorMasterService.findAll();
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("bugTickets", bugTicketService.findAll());
        return "bugs/list";
    }

    @GetMapping("/new")
    public String showForm(@ModelAttribute("bugTicketForm") BugTicketForm form) {
        if (form.getReportedOn() == null) {
            form.setReportedOn(LocalDate.now());
        }
        return "bugs/form";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("bugTicketForm") BugTicketForm form,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bugs/form";
        }

        bugTicketService.register(new BugTicketRegistration(
                form.getTitle(),
                form.getDescription(),
                form.getStatus(),
                form.getPriority(),
                form.getReportedOn(),
                form.getSystemCode(),
                form.getProjectNo(),
                form.getVendorCode()
        ));
        redirectAttributes.addFlashAttribute("message", "バグを登録しました");
        return "redirect:/bugs";
    }

    @GetMapping("/trends")
    public String trends(Model model) {
        BugTrendSummary summary = bugTicketService.getMonthlyTrend(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");

        List<String> labels = summary.months().stream()
                .map(month -> month.format(formatter))
                .toList();

        Map<String, List<Long>> statusCounts = new LinkedHashMap<>();
        Map<String, String> displayNames = new LinkedHashMap<>();
        Map<String, String> backgroundColors = new LinkedHashMap<>();
        Map<String, String> borderColors = new LinkedHashMap<>();

        for (BugStatus status : BugStatus.values()) {
            String key = status.name();
            statusCounts.put(key, summary.statusCounts().get(status));
            displayNames.put(key, status.displayName());
            backgroundColors.put(key, switch (status) {
                case NEW -> "rgba(54, 162, 235, 0.2)";
                case IN_PROGRESS -> "rgba(255, 159, 64, 0.2)";
                case RESOLVED -> "rgba(75, 192, 192, 0.2)";
                case CLOSED -> "rgba(201, 203, 207, 0.2)";
            });
            borderColors.put(key, switch (status) {
                case NEW -> "rgba(54, 162, 235, 1)";
                case IN_PROGRESS -> "rgba(255, 159, 64, 1)";
                case RESOLVED -> "rgba(75, 192, 192, 1)";
                case CLOSED -> "rgba(201, 203, 207, 1)";
            });
        }

        model.addAttribute("labels", labels);
        model.addAttribute("statusCounts", statusCounts);
        model.addAttribute("statusDisplayNames", displayNames);
        model.addAttribute("statusBackgroundColors", backgroundColors);
        model.addAttribute("statusBorderColors", borderColors);

        return "bugs/trends";
    }
}
