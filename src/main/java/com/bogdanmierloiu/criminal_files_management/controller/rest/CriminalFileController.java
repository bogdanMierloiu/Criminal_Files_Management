package com.bogdanmierloiu.criminal_files_management.controller.rest;

import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileRequest;
import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileResponse;
import com.bogdanmierloiu.criminal_files_management.exception.SolutionDescriptionException;
import com.bogdanmierloiu.criminal_files_management.service.CriminalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/criminal-file")
@RequiredArgsConstructor
public class CriminalFileController implements CrudController<CriminalFileRequest, CriminalFileResponse> {
    private final CriminalFileService criminalFileService;

    @PostMapping
    public ResponseEntity<CriminalFileResponse> add(@RequestBody CriminalFileRequest criminalFileRequest) {
        return new ResponseEntity<>(criminalFileService.add(criminalFileRequest), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CriminalFileResponse>> getAll() {
        return new ResponseEntity<>(criminalFileService.getAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CriminalFileResponse> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(criminalFileService.findById(id), HttpStatus.OK);
    }

    @Override
    @PutMapping
    public ResponseEntity<CriminalFileResponse> update(@RequestBody CriminalFileRequest request) {
        try {
            return new ResponseEntity<>(criminalFileService.update(request), HttpStatus.OK);
        } catch (SolutionDescriptionException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        criminalFileService.delete(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importFiles() {
        criminalFileService.importFiles();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/unknown-author-list")
    public ResponseEntity<List<CriminalFileResponse>> getAllWithUnknownAuthor() {
        return new ResponseEntity<>(criminalFileService.listWithUnknownAuthor(), HttpStatus.OK);
    }

    @GetMapping("/unknown-author-in-progress")
    public ResponseEntity<List<CriminalFileResponse>> getAllWithUnknownAuthorProgress() {
        return new ResponseEntity<>(criminalFileService.listWithUnknownAuthorInProgress(), HttpStatus.OK);
    }

    @GetMapping("/known-author-list")
    public ResponseEntity<List<CriminalFileResponse>> getAllWithKnownAuthor() {
        return new ResponseEntity<>(criminalFileService.listWithKnownAuthor(), HttpStatus.OK);
    }
}
