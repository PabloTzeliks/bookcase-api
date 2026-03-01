package com.centroweg.pablo.librarysystem.app.service;

import com.centroweg.pablo.librarysystem.app.dto.AddNewBookRequest;
import com.centroweg.pablo.librarysystem.app.dto.BookResponse;
import com.centroweg.pablo.librarysystem.app.mapper.BookMapper;
import com.centroweg.pablo.librarysystem.domain.UserBook;
import com.centroweg.pablo.librarysystem.domain.common.exception.BusinessRuleException;
import com.centroweg.pablo.librarysystem.infra.persistence.repository.JpaBookRepository;
import com.centroweg.pablo.librarysystem.infra.persistence.repository.JpaUserBookRepository;
import com.centroweg.pablo.librarysystem.infra.persistence.repository.JpaUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final JpaBookRepository bookRepository;
    private final JpaUserBookRepository relationalRepository;
    private final JpaUserRepository userRepository;
    private final BookMapper mapper;

    public BookService(JpaBookRepository bookRepository,
                       JpaUserBookRepository relationalRepository,
                       JpaUserRepository userRepository,
                       BookMapper mapper) {

        this.bookRepository = bookRepository;
        this.relationalRepository = relationalRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public BookResponse save(AddNewBookRequest request, String emailLogin) {

        var loggedUser = userRepository.findByEmail(emailLogin)
                .orElseThrow(() -> new BusinessRuleException("User does not have access."));

        var book = bookRepository.findByIsbn(request.isbn())
                .orElseGet(() -> bookRepository.save(mapper.toDomain(request, null)));

        var relation = relationalRepository.save(new UserBook(
                null,
                loggedUser,
                book,
                request.status(),
                request.rating()
        ));

        return mapper.toDto(relation);
    }

    public List<BookResponse> listUserBooks(String emailLogin) {

        var loggedUser = userRepository.findByEmail(emailLogin)
                .orElseThrow(() -> new BusinessRuleException("User does not have access."));

        List<UserBook> userBooks = relationalRepository.findByUser(loggedUser);

        return userBooks.stream()
                .map(mapper::toDto)
                .toList();
    }
}
