package com.bogdanmierloiu.criminal_files_management.controller.web;

import com.bogdanmierloiu.criminal_files_management.dto.AuthorRequest;
import com.bogdanmierloiu.criminal_files_management.dto.AuthorResponse;
import com.bogdanmierloiu.criminal_files_management.entity.Author;
import com.bogdanmierloiu.criminal_files_management.mapper.AuthorMapper;
import com.bogdanmierloiu.criminal_files_management.service.AuthorService;
import com.bogdanmierloiu.criminal_files_management.service.CriminalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorWebController {
    private final AuthorService authorService;

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

}
