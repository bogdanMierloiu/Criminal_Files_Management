package com.bogdanmierloiu.criminal_files_management.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class CriminalFileResponse {

    private Long id;
    private Long registrationNumberPS;
    private LocalDate registrationDate;
    private String registrationNumberProsecutor;
    private String legalQualification;
    @JsonProperty
    private Boolean isResolved;
    private LocalDate resolutionDate;
    private String solutionDescription;
    private CrimeTypeResponse crimeTypeResponse;
    private List<AuthorResponse> authorResponseList;


}
