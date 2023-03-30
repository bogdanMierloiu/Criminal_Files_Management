package com.bogdanmierloiu.criminal_files_management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CrimeTypeRequest {
    private Long id;
    @NotBlank
    private String type;

}