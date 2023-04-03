package com.bogdanmierloiu.criminal_files_management.controller.web;

import com.bogdanmierloiu.criminal_files_management.dto.AuthorResponse;
import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileRequest;
import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileResponse;
import com.bogdanmierloiu.criminal_files_management.entity.CriminalFile;
import com.bogdanmierloiu.criminal_files_management.exception.SolutionDescriptionException;
import com.bogdanmierloiu.criminal_files_management.service.AuthorService;
import com.bogdanmierloiu.criminal_files_management.service.CrimeTypeService;
import com.bogdanmierloiu.criminal_files_management.service.CriminalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/criminal-file")
public class CriminalFileWebController {
    private final AuthorService authorService;
    private final CrimeTypeService crimeTypeService;
    private final CriminalFileService criminalFileService;

    @GetMapping("/goToAddCriminalFile")
    public String goToAddCriminalFile(Model model) {
        model.addAttribute("crimeTypesList", crimeTypeService.getAll());
        model.addAttribute("authorList", authorService.getAll());
        return "criminal-file-form";
    }

    @PostMapping("/add-criminal-file")
    public String addAuthor(@ModelAttribute CriminalFileRequest request, Model model) {
        CriminalFileResponse criminalFileResponse = criminalFileService.add(request);
        model.addAttribute("listWithUnknownAuthor", criminalFileService.listWithUnknownAuthorInProgress());
        model.addAttribute("listWithKnownAuthor", criminalFileService.listWithKnownAuthorInProgress());
        if (criminalFileResponse.getAuthorResponseList().isEmpty()) {
            return "criminalFilesAN";
        } else {
            return "criminalFilesAC";
        }
    }

    @GetMapping("/deleteById/{id}")
    public String delete(@PathVariable Long id, Model model) {
        CriminalFileResponse criminalFileResponse = criminalFileService.findById(id);
        criminalFileService.delete(id);
        model.addAttribute("listWithUnknownAuthor", criminalFileService.listWithUnknownAuthorInProgress());
        model.addAttribute("listWithKnownAuthor", criminalFileService.listWithKnownAuthorInProgress());
        if (criminalFileResponse.getAuthorResponseList().isEmpty()) {
            return "criminalFilesAN";
        } else {
            return "criminalFilesAC";
        }
    }

    @GetMapping("/updateById/{id}")
    public String goToUpdateForm(@PathVariable Long id, Model model) {
        CriminalFileRequest criminalFileRequest = new CriminalFileRequest();
        CriminalFile criminalFile = criminalFileService.findEntityById(id);
        criminalFileRequest.setRegistrationNumberPS(criminalFile.getRegistrationNumberPS());
        criminalFileRequest.setRegistrationDate(criminalFile.getRegistrationDate());
        criminalFileRequest.setRegistrationNumberProsecutor(criminalFile.getRegistrationNumberProsecutor());
        criminalFileRequest.setLegalQualification(criminalFile.getLegalQualification());
        criminalFileRequest.setId(id);
        model.addAttribute("criminalFileSelected", criminalFileRequest);
        model.addAttribute("crimeTypesList", crimeTypeService.getAll());
        model.addAttribute("authorsList", authorService.getAll());
        return "criminal-file-form-update";
    }

    @PostMapping("/update-criminal-file")
    public String updateCriminalFile(@ModelAttribute CriminalFileRequest request, Model model) throws SolutionDescriptionException {
        CriminalFileResponse fileResponse = criminalFileService.update(request);
        model.addAttribute("listWithUnknownAuthor", criminalFileService.listWithUnknownAuthorInProgress());
        model.addAttribute("listWithKnownAuthor", criminalFileService.listWithKnownAuthorInProgress());
        if (fileResponse.getAuthorResponseList().isEmpty()) {
            return "criminalFilesAN";
        } else {
            return "criminalFilesAC";
        }
    }


    @GetMapping("/resolveById/{id}")
    public String goToResolveForm(@PathVariable Long id, Model model) {
        CriminalFileResponse criminalFileResponse = criminalFileService.findById(id);
        model.addAttribute("criminalFileSelected", criminalFileResponse);
        return "criminal-file-form-resolve";
    }

    @PostMapping("/resolve")
    public String updateAuthor(@ModelAttribute CriminalFileResponse request, Model model) throws SolutionDescriptionException {
        CriminalFileRequest criminalFileToResolve = new CriminalFileRequest();
        BeanUtils.copyProperties(request, criminalFileToResolve);
        criminalFileToResolve.setIsResolved(true);
        criminalFileService.update(criminalFileToResolve);
        model.addAttribute("listWithUnknownAuthor", criminalFileService.listWithUnknownAuthorInProgress());
        model.addAttribute("listWithKnownAuthor", criminalFileService.listWithKnownAuthorInProgress());
        if (criminalFileToResolve.getAuthorsId().isEmpty()) {
            return "criminalFilesAN";
        } else {
            return "criminalFilesAC";
        }
    }

    @GetMapping("/find")
    public String findByNumber(@RequestParam("searchTerm") String searchTerm, Model model) {
        long longSearchTerm;
        if (searchTerm.matches("\\d+")) {
            longSearchTerm = Long.parseLong(searchTerm);
            List<CriminalFileResponse> filesFound = criminalFileService.findByRegistrationNumber(longSearchTerm, searchTerm);
            model.addAttribute("filesFound", filesFound);
            return "criminalFilesFound";
        } else {
            List<AuthorResponse> authorsFound = authorService.findByName(searchTerm, searchTerm);
            model.addAttribute("authorsFound", authorsFound);
            return "authorsFound";
        }
    }

}
