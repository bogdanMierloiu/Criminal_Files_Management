package com.bogdanmierloiu.criminal_files_management.controller.rest;

import com.bogdanmierloiu.criminal_files_management.dto.AuthorRequest;
import com.bogdanmierloiu.criminal_files_management.dto.AuthorResponse;
import com.bogdanmierloiu.criminal_files_management.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController implements CrudController<AuthorRequest, AuthorResponse> {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponse> add(@RequestBody AuthorRequest author) {
        return new ResponseEntity<>(authorService.add(author), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAll() {
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(authorService.findById(id), HttpStatus.OK);
    }

    @Override
    @PutMapping
    public ResponseEntity<AuthorResponse> update(@RequestBody AuthorRequest request) {
        return new ResponseEntity<>(authorService.update(request), HttpStatus.ACCEPTED);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }
}
