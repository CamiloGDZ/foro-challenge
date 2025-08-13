package com.forohub.controlador;

import com.forohub.topico.dto.DatosRegistroUsuario;
import com.forohub.usuario.modelo.Usuario;
import com.forohub.usuario.repositorio.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        usuarioRepository.save(new Usuario(datosRegistroUsuario));
        return ResponseEntity.ok().build();
    }
}