package com.bogdanmierloiu.criminal_files_management.controller.web;

import com.bogdanmierloiu.criminal_files_management.service.AuthorService;
import com.bogdanmierloiu.criminal_files_management.service.CrimeTypeService;
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
    private final CrimeTypeService crimeTypeService;
    private final AuthorService authorService;

    @GetMapping
    public String goToIndexPage(Model model) {
        model.addAttribute("nrOfFilesWithUnknownAuthor", criminalFileService.countUnknownAuthorInProgress());
        model.addAttribute("nrOfFilesWithKnownAuthor", criminalFileService.countKnownAuthorInProgress());
        model.addAttribute("activePage", "authors");
        model.addAttribute("activePage", "criminalFilesAN");
        model.addAttribute("activePage", "criminalFilesAC");

        return "index";
    }

    @GetMapping("/goToCriminalFilesAN")
    public String goToCriminalFilesWithAN(Model model) {
        model.addAttribute("listWithUnknownAuthor", criminalFileService.listWithUnknownAuthorInProgress());
        return "criminalFilesAN";
    }

    @GetMapping("/goToCriminalFilesAC")
    public String goToCriminalFilesWithAC(Model model) {
        model.addAttribute("listWithKnownAuthor", criminalFileService.listWithKnownAuthorInProgress());
        return "criminalFilesAC";
    }

    @GetMapping("/goToAuthors")
    public String goToAuthors(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "authors";
    }

    @GetMapping("/goToCrimeTypes")
    public String goToCrimeTypes(Model model) {
        model.addAttribute("crimeTypeList", crimeTypeService.getAll());
        return "crime-types";
    }
}
