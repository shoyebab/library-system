package com.example.controller;

import com.example.dto.AuthorDto;
import com.example.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDto> create(@Valid @RequestBody AuthorDto dto) {
        return ResponseEntity.ok(authorService.createAuthor(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable Long id, @Valid @RequestBody AuthorDto dto) {
        return ResponseEntity.ok(authorService.updateAuthor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<AuthorDto>> listAll(Pageable pageable) {
        return ResponseEntity.ok(authorService.getAllAuthors(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<AuthorDto>> search(@RequestParam String keyword, Pageable pageable) {
        return ResponseEntity.ok(authorService.searchAuthors(keyword, pageable));
    }
}