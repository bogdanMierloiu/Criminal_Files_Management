package com.bogdanmierloiu.criminal_files_management.controller;

import com.bogdanmierloiu.criminal_files_management.entity.Author;
import com.bogdanmierloiu.criminal_files_management.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> add(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.add(author), HttpStatus.OK);
    }
}
