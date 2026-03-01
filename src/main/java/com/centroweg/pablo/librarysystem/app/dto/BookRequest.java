package com.centroweg.pablo.librarysystem.app.dto;

import com.centroweg.pablo.librarysystem.domain.BookStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record BookRequest(
        @NotBlank
        @JsonProperty("isbn")
        String isbn,

        @NotBlank
        @JsonProperty("title")
        String title,

        @NotBlank
        @JsonProperty("authors")
        String authors,

        @NotBlank
        @URL
        @JsonProperty("thumbnail_url")
        String thumbnailUrl,

        @NotBlank
        @JsonProperty("status")
        BookStatus status
) { }
