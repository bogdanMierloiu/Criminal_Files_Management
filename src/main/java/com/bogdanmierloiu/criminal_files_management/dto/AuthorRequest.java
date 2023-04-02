package com.bogdanmierloiu.criminal_files_management.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AuthorRequest {
    private Long id;
    @NotBlank(message = "Last name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last name must contain only letters")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last name must contain only letters")
    private String lastName;

    @Length(min = 13, max = 13, message = "The personal code must be exactly 13 characters")
    @Pattern(regexp = "^[0-9]*$", message = "Only numeric characters are allowed")
    @Column(name = "personal_numerical_code", unique = true, nullable = false)
    private String personalCode;


}