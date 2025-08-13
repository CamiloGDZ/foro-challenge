package com.forohub.topico.dto;

import com.forohub.topico.modelo.StatusTopico;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull Long id,
        String titulo,
        String mensaje,
        StatusTopico status,
        String curso
) {
}