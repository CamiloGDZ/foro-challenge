package com.forohub.infra.security; // Asegúrate de que este sea el paquete correcto

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosAutenticacionUsuario(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 6)
        String clave
) {
}