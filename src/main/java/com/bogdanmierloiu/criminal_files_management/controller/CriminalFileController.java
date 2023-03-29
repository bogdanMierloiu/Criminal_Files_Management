package com.bogdanmierloiu.criminal_files_management.controller;

import com.bogdanmierloiu.criminal_files_management.dto.CriminalFileRequest;
import com.bogdanmierloiu.criminal_files_management.entity.CriminalFile;
import com.bogdanmierloiu.criminal_files_management.service.CriminalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/criminal-file")
@RequiredArgsConstructor
public class CriminalFileController {
    private final CriminalFileService criminalFileService;

    @PostMapping
    public ResponseEntity<CriminalFile> add(@RequestBody CriminalFileRequest criminalFileRequest) {
        return new ResponseEntity<>(criminalFileService.addCriminalFile(criminalFileRequest), HttpStatus.OK);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importFiles() {
        criminalFileService.importFiles();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
