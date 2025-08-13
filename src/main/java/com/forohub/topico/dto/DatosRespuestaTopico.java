package com.forohub.topico.dto;

import com.forohub.topico.modelo.StatusTopico;
import com.forohub.topico.modelo.Topico;
import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String autor,
        String curso
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor().getNombre(), topico.getCurso());
    }
}