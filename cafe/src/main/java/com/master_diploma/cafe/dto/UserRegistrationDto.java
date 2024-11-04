package com.master_diploma.cafe.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private long phone;
    private String password;
}
