package com.centroweg.pablo.librarysystem.infra.web.controller;

import com.centroweg.pablo.librarysystem.app.dto.AddNewBookRequest;
import com.centroweg.pablo.librarysystem.app.dto.BookResponse;
import com.centroweg.pablo.librarysystem.app.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookResponse> add(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody AddNewBookRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request, userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> listUserBooks(@AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(service.listUserBooks(userDetails.getUsername()));
    }
}
