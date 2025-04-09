package com.example.service.impl;

import com.example.dto.AuthorDto;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Author;
import com.example.repository.AuthorRepository;
import com.example.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private AuthorDto mapToDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .bio(author.getBio())
                .build();
    }

    private Author mapToEntity(AuthorDto dto) {
        return Author.builder()
                .id(dto.getId())
                .name(dto.getName())
                .bio(dto.getBio())
                .build();
    }

    public AuthorDto createAuthor(AuthorDto dto) {
        return mapToDto(authorRepository.save(mapToEntity(dto)));
    }

    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return mapToDto(author);
    }

    public AuthorDto updateAuthor(Long id, AuthorDto dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        author.setName(dto.getName());
        author.setBio(dto.getBio());
        return mapToDto(authorRepository.save(author));
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public Page<AuthorDto> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable).map(this::mapToDto);
    }

    public Page<AuthorDto> searchAuthors(String keyword, Pageable pageable) {
        return authorRepository.findByNameContainingIgnoreCase(keyword, pageable).map(this::mapToDto);
    }
}