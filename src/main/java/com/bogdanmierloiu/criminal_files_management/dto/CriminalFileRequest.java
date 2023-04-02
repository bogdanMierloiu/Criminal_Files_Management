package com.bogdanmierloiu.criminal_files_management.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
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
    @NotBlank
    private String legalQualification;
    @JsonProperty
    private Boolean isResolved = false;
    private LocalDate resolutionDate;
    private String solutionDescription;
    private Long crimeTypeId = null;
    private List<Long> authorsId = new ArrayList<>();


}
