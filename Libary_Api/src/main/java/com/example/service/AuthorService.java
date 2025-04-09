package com.example.service;

import com.example.dto.AuthorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    AuthorDto createAuthor(AuthorDto dto);

    AuthorDto getAuthorById(Long id);

    AuthorDto updateAuthor(Long id, AuthorDto dto);

    void deleteAuthor(Long id);

    Page<AuthorDto> getAllAuthors(Pageable pageable);

    Page<AuthorDto> searchAuthors(String keyword, Pageable pageable);
}