package com.bogdanmierloiu.criminal_files_management.controller;

import com.bogdanmierloiu.criminal_files_management.entity.Author;
import com.bogdanmierloiu.criminal_files_management.entity.CrimeType;
import com.bogdanmierloiu.criminal_files_management.service.CrimeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crime-type")
@RequiredArgsConstructor
public class CrimeTypeController {
    private final CrimeTypeService crimeTypeService;

    @PostMapping
    public ResponseEntity<CrimeType> add(@RequestBody CrimeType crimeType) {
        return new ResponseEntity<>(crimeTypeService.add(crimeType), HttpStatus.OK);
    }
}
