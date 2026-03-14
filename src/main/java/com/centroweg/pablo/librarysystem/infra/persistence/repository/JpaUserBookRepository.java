package com.centroweg.pablo.librarysystem.infra.persistence.repository;

import com.centroweg.pablo.librarysystem.domain.User;
import com.centroweg.pablo.librarysystem.domain.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaUserBookRepository extends JpaRepository<UserBook, Long> {

    List<UserBook> findByUser(User user);

    // Testando implementação com IN
    List<UserBook> findByIdIn(List<Long> ids);
}
