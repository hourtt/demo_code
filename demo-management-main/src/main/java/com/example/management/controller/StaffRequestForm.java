package com.example.management.controller;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class StaffRequestForm {

    @NotBlank(message = "loginId is required")
    @Size(min = 5, max = 10, message = "loginId size 5 ~ 10")
    private String loginId;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "name is required")
    private String name;
    private LocalDate birthDate;
    @NotBlank(message = "email is required")
    @Email(message = "email format id@domain")
    private String email;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
