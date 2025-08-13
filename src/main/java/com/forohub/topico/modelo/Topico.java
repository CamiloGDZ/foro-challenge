package com.forohub.topico.modelo;

import com.forohub.topico.dto.DatosActualizarTopico;
import com.forohub.topico.dto.DatosRegistroTopico;
import com.forohub.usuario.modelo.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private StatusTopico status;
    private String curso;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario autor) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = StatusTopico.ABIERTO;
        this.curso = datosRegistroTopico.curso();
        this.autor = autor;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.status() != null) {
            this.status = datosActualizarTopico.status();
        }
        if (datosActualizarTopico.curso() != null) {
            this.curso = datosActualizarTopico.curso();
        }
    }
}