package noemipusceddu.U2W3L5be.payloads.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UserDTO(
        @NotEmpty(message = "Questo campo è obbligatorio")
        @NotBlank
        String name,
        @NotEmpty(message = "Questo campo è obbligatorio")
        @NotBlank
        String surname,
        @NotEmpty(message = "Questo campo è obbligatorio")
        @Email
        String email,
        @NotEmpty(message = "Questo campo è obbligatorio")
        String password,
        @NotEmpty(message = "Questo campo è obbligatorio")
        String role
) {
}
