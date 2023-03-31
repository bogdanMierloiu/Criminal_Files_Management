package com.bogdanmierloiu.criminal_files_management.controller.rest;

import com.bogdanmierloiu.criminal_files_management.dto.CrimeTypeRequest;
import com.bogdanmierloiu.criminal_files_management.dto.CrimeTypeResponse;
import com.bogdanmierloiu.criminal_files_management.service.CrimeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crime-type")
@RequiredArgsConstructor
public class CrimeTypeController implements CrudController<CrimeTypeRequest, CrimeTypeResponse>{
    private final CrimeTypeService crimeTypeService;

    @PostMapping
    public ResponseEntity<CrimeTypeResponse> add(@RequestBody CrimeTypeRequest crimeType) {
        return new ResponseEntity<>(crimeTypeService.add(crimeType), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CrimeTypeResponse>> getAll() {
        return new ResponseEntity<>(crimeTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrimeTypeResponse> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(crimeTypeService.findById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CrimeTypeResponse> update(@RequestBody CrimeTypeRequest crimeTypeRequest) {
        return new ResponseEntity<>(crimeTypeService.update(crimeTypeRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        crimeTypeService.delete(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }
}
