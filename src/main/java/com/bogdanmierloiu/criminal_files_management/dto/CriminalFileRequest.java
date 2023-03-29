package com.bogdanmierloiu.criminal_files_management.dto;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class CriminalFileRequest {

    private Long id;
    private Long registrationNumberPS;
    private LocalDate registrationDate;
    private String registrationNumberProsecutor;
    private String legalQualification;
    private Long crimeTypeId;


}
