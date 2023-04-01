package com.bogdanmierloiu.criminal_files_management.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class CriminalFileRequest {

    private Long id;
    @NotBlank
    private Long registrationNumberPS;
    @NotBlank
    private LocalDate registrationDate;
    @NotBlank
    private String registrationNumberProsecutor;
    private String legalQualification;
    private boolean isResolved = false;
    private LocalDate resolutionDate;
    private String solutionDescription;
    private Long crimeTypeId = null;
    private List<Long> authorsId = new ArrayList<>();


}
