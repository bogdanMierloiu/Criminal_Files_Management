package com.bogdanmierloiu.criminal_files_management.dto;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class CriminalFileResponse {

    private Long id;
    private Long registrationNumberPS;
    private LocalDate registrationDate;
    private String registrationNumberProsecutor;
    private String legalQualification;
    private boolean isResolved;
    private LocalDate resolutionDate;
    private String solutionDescription;
    private CrimeTypeResponse crimeTypeResponse;
    private List<AuthorResponse> authorResponseList;


}
