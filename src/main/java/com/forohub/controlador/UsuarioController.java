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
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        var usuario = new Usuario(datosRegistroUsuario);
        usuario.setClave(passwordEncoder.encode(datosRegistroUsuario.clave()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}