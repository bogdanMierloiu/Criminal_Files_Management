package com.bogdanmierloiu.criminal_files_management.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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
    @Digits(integer = 10, message = "Registration number must be a 10-digit number", fraction = 0)
    private Long registrationNumberPS;
    @NotBlank
    @PastOrPresent(message = "Registration date must be in the past or present")
    private LocalDate registrationDate;
    @NotBlank
    private String registrationNumberProsecutor;
    private String legalQualification;
    @JsonProperty
    private boolean isResolved = false;
    private LocalDate resolutionDate;
    private String solutionDescription;
    private Long crimeTypeId = null;
    private List<Long> authorsId = new ArrayList<>();


}
