package com.bogdanmierloiu.criminal_files_management.controller.web;

import com.bogdanmierloiu.criminal_files_management.dto.AuthorRequest;
import com.bogdanmierloiu.criminal_files_management.dto.AuthorResponse;
import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileRequest;
import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileResponse;
import com.bogdanmierloiu.criminal_files_management.service.AuthorService;
import com.bogdanmierloiu.criminal_files_management.service.CrimeTypeService;
import com.bogdanmierloiu.criminal_files_management.service.CriminalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("listWithUnknownAuthor", criminalFileService.listWithUnknownAuthor());
        model.addAttribute("listWithKnownAuthor", criminalFileService.listWithKnownAuthor());
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
        model.addAttribute("listWithUnknownAuthor", criminalFileService.listWithUnknownAuthor());
        model.addAttribute("listWithKnownAuthor", criminalFileService.listWithKnownAuthor());
        if (criminalFileResponse.getAuthorResponseList().isEmpty()) {
            return "criminalFilesAN";
        } else {
            return "criminalFilesAC";
        }
    }

//    @GetMapping("/updateById/{id}")
//    public String goToUpdateForm(@PathVariable Long id, Model model) {
//        AuthorResponse author = authorService.findById(id);
//        model.addAttribute("authorSelected", author);
//        return "author-form-update";
//    }
//
//    @PostMapping("/update-author")
//    public String updateAuthor(@ModelAttribute AuthorResponse request, Model model) {
//        AuthorRequest authorToUpdate = new AuthorRequest();
//        BeanUtils.copyProperties(request, authorToUpdate);
//        authorService.update(authorToUpdate);
//        model.addAttribute("authors", authorService.getAll());
//        return "authors";
//    }

}
