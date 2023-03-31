package com.bogdanmierloiu.criminal_files_management.controller.web;

import com.bogdanmierloiu.criminal_files_management.service.CriminalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class IndexController {

    private final CriminalFileService criminalFileService;

    @GetMapping
    public String goToIndexPage() {
        return "index";
    }

    @GetMapping("/goToCriminalFilesAN")
    public String goToCriminalFilesWithAN(Model model) {
        model.addAttribute("criminalFilesAN", criminalFileService.listWithUnknownAuthor());
        return "criminalFilesAN";
    }

    @GetMapping("/goToCriminalFilesAC")
    public String goToCriminalFilesWithAC(Model model) {
        model.addAttribute("criminalFilesAC", criminalFileService.listWithKnownAuthor());
        return "criminalFilesAC";
    }
}
