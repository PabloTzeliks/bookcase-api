package com.centroweg.pablo.librarysystem.app.mapper;

import com.centroweg.pablo.librarysystem.app.dto.AddNewBookRequest;
import com.centroweg.pablo.librarysystem.app.dto.BookRequest;
import com.centroweg.pablo.librarysystem.app.dto.BookResponse;
import com.centroweg.pablo.librarysystem.domain.Book;
import com.centroweg.pablo.librarysystem.domain.UserBook;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toDomain(BookRequest request, Long id) {

        return new Book(
                id,
                request.isbn(),
                request.title(),
                request.authors(),
                request.thumbnailUrl()
        );
    }

    public Book toDomain(AddNewBookRequest request, Long id) {

        return new Book(
                id,
                request.isbn(),
                request.title(),
                request.authors(),
                request.thumbnailUrl()
        );
    }

    public BookResponse toDto(UserBook relation) {

        return new BookResponse(
                relation.getBook().getId(),
                relation.getBook().getIsbn(),
                relation.getBook().getTitle(),
                relation.getBook().getAuthor(),
                relation.getBook().getThumbnailUrl(),
                relation.getStatus(),
                relation.getRating()
        );
    }
}
