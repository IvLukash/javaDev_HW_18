package goit.edu.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NoteCreateOrUpdateDto(
        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 100, message = "Title length should be between 3 and 100")
        String title,
        @NotBlank(message = "Content is required")
        String content
) {
}
