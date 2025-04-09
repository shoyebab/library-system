package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;

    @NotBlank(message = "Book title is required")
    private String title;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotNull(message = "Published date is required")
    private LocalDate publishedDate;

    @NotNull(message = "Author ID is required")
    private Long authorId;
}
