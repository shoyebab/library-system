package com.example.service;

import com.example.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto createBook(BookDto dto);
    BookDto getBookById(Long id);
    BookDto updateBook(Long id, BookDto dto);
    void deleteBook(Long id);
    Page<BookDto> getAllBooks(Pageable pageable);
    Page<BookDto> searchBooks(String keyword, Pageable pageable);
}
