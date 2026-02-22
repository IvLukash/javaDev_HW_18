package goit.edu.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime time,
        int status,
        String massage
) {
}
