package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {
    private Long id;

    @NotBlank(message = "Author name is required")
    private String name;

    @NotBlank(message = "Biography is required")
    private String bio;
}