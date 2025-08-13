package com.forohub.usuario.modelo;

import com.forohub.topico.dto.DatosRegistroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String clave;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.nombre = datosRegistroUsuario.nombre();
        this.email = datosRegistroUsuario.email();
        this.clave = datosRegistroUsuario.clave();
    }
}