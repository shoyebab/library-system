package com.example.service;

import com.example.dto.AuthorDto;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Author;
import com.example.repository.AuthorRepository;
import com.example.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAuthor() {
        AuthorDto dto = AuthorDto.builder().name("Author A").bio("Bio A").build();
        Author saved = Author.builder().id(1L).name("Author A").bio("Bio A").build();

        when(authorRepository.save(any(Author.class))).thenReturn(saved);
        AuthorDto result = authorService.createAuthor(dto);

        assertThat(result.getId()).isEqualTo(1L);
        verify(authorRepository, times(1)).save(any());
    }

    @Test
    void testGetAuthorById_NotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> authorService.getAuthorById(1L));
    }
}
