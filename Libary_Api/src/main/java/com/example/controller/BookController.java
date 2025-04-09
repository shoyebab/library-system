package com.example.controller;

import com.example.dto.BookDto;
import com.example.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> create(@Valid @RequestBody BookDto dto) {
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id, @Valid @RequestBody BookDto dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<BookDto>> listAll(Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BookDto>> search(@RequestParam String keyword, Pageable pageable) {
        return ResponseEntity.ok(bookService.searchBooks(keyword, pageable));
    }
}
