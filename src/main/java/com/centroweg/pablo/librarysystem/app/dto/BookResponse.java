package com.centroweg.pablo.librarysystem.app.dto;

import com.centroweg.pablo.librarysystem.domain.BookStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BookResponse(

        @JsonProperty("id")
        Long id,

        @JsonProperty("isbn")
        String isbn,

        @JsonProperty("title")
        String title,

        @JsonProperty("authors")
        String authors,

        @JsonProperty("thumbnail_url")
        String thumbnailUrl,

        @JsonProperty("status")
        BookStatus status,

        @JsonProperty("rating")
        int rating
) { }
