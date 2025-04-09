package com.example.service.impl;

import com.example.dto.BookDto;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Author;
import com.example.model.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import com.example.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private BookDto mapToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publishedDate(book.getPublishedDate())
                .authorId(book.getAuthor().getId())
                .build();
    }

    private Book mapToEntity(BookDto dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .isbn(dto.getIsbn())
                .publishedDate(dto.getPublishedDate())
                .author(author)
                .build();
    }

    public BookDto createBook(BookDto dto) {
        return mapToDto(bookRepository.save(mapToEntity(dto)));
    }

    public BookDto getBookById(Long id) {
        return mapToDto(bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found")));
    }

    public BookDto updateBook(Long id, BookDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublishedDate(dto.getPublishedDate());
        book.setAuthor(authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found")));
        return mapToDto(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Page<BookDto> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(this::mapToDto);
    }

    public Page<BookDto> searchBooks(String keyword, Pageable pageable) {
        return bookRepository
                .findByTitleContainingIgnoreCaseOrAuthor_NameContainingIgnoreCase(keyword, keyword, pageable)
                .map(this::mapToDto);
    }
}
