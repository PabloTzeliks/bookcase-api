package com.centroweg.pablo.librarysystem.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(

        @JsonProperty("id")
        Long id,

        @JsonProperty("name")
        String name,

        @JsonProperty("email")
        String email
) { }
