package noemipusceddu.U2W3L5be.payloads.reservation;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReservationDTO(
        @NotNull(message = "Questo campo è obbligatorio")
        UUID idUser,
        @NotNull(message = "Questo campo è obbligatorio")
        UUID idEvent) {
}
