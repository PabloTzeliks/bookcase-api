package com.centroweg.pablo.librarysystem.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequest(

        @NotBlank
        @JsonProperty("name")
        String name,

        @NotBlank
        @Email
        @JsonProperty("email")
        String email,

        @NotBlank
        @JsonProperty("password")
        String password
) { }
