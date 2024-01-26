package noemipusceddu.U2W3L5be.payloads.event;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record EventDTO(
        @NotEmpty(message = "Questo campo è obbligatorio")
        String title,
        @NotEmpty(message = "Questo campo è obbligatorio")
        String description,
        @NotEmpty(message = "Questo campo è obbligatorio")
        String place,
        @NotNull(message = "Questo campo è obbligatorio")
        Integer availableSeats,
        @NotEmpty(message = "Questo campo è obbligatorio")
        String image,
        @NotNull(message = "Questo campo è obbligatorio")
        UUID idAdmin
) {
}
