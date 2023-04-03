package com.bogdanmierloiu.criminal_files_management.controller.web;

import com.bogdanmierloiu.criminal_files_management.dto.AuthorRequest;
import com.bogdanmierloiu.criminal_files_management.dto.AuthorResponse;
import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileResponse;
import com.bogdanmierloiu.criminal_files_management.service.AuthorService;
import com.bogdanmierloiu.criminal_files_management.service.CriminalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorWebController {
    private final AuthorService authorService;
    private final CriminalFileService criminalFileService;

    @GetMapping("/goToAddAuthor")
    public String goToAddAuthor() {
        return "author-form";
    }

    @PostMapping("/add-author")
    public String addAuthor(@ModelAttribute AuthorRequest request, Model model) {
        authorService.add(request);
        model.addAttribute("authors", authorService.getAll());
        return "authors";
    }

    @GetMapping("/deleteById/{id}")
    public String delete(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/goToAuthors";
    }

    @GetMapping("/updateById/{id}")
    public String goToUpdateForm(@PathVariable Long id, Model model) {
        AuthorResponse author = authorService.findById(id);
        model.addAttribute("authorSelected", author);
        return "author-form-update";
    }

    @PostMapping("/update-author")
    public String updateAuthor(@ModelAttribute AuthorResponse request, Model model) {
        AuthorRequest authorToUpdate = new AuthorRequest();
        BeanUtils.copyProperties(request, authorToUpdate);
        authorService.update(authorToUpdate);
        model.addAttribute("authors", authorService.getAll());
        return "authors";
    }

    @GetMapping("/filesById/{id}")
    public String filesById(@PathVariable Long id, Model model) {
        List<CriminalFileResponse> filesForAuthor = criminalFileService.findByAuthorId(id);
        model.addAttribute("listWithKnownAuthor", filesForAuthor);
        return "criminalFilesAC";
    }

}
