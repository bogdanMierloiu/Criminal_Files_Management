package com.bogdanmierloiu.criminal_files_management.dto;

import lombok.Data;

@Data
public class AuthorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String personalCode;


}