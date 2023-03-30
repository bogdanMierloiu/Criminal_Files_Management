package com.bogdanmierloiu.criminal_files_management.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AuthorRequest {
    private Long id;
    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String lastName;

    @Length(min = 13, max = 13, message = "The personal code must be exactly 13 characters")
    @Pattern(regexp = "^[0-9]*$", message = "Only numeric characters are allowed")
    @Column(name = "personal_numerical_code", unique = true, nullable = false)
    private String personalCode;


}