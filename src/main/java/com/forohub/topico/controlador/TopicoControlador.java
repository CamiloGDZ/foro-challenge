package com.forohub.topico.controlador;

import com.forohub.topico.dto.DatosActualizarTopico;
import com.forohub.topico.dto.DatosRegistroTopico;
import com.forohub.topico.dto.DatosRespuestaTopico;
import com.forohub.topico.modelo.Topico;
import com.forohub.topico.repositorio.TopicoRepository;
import com.forohub.usuario.modelo.Usuario;
import com.forohub.usuario.repositorio.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoControlador {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder) {
        // Validar si el tópico ya existe (mismo título y mensaje)
        if (topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            return ResponseEntity.badRequest().body(null);
        }

        // Buscar al autor por email para obtener el objeto Usuario completo
        Optional<Usuario> autorOptional = Optional.ofNullable(usuarioRepository.findByEmail(datosRegistroTopico.autor()));
        if (autorOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Usuario autor = autorOptional.get();

        // Crear el tópico y guardarlo en la base de datos
        Topico topico = new Topico(datosRegistroTopico, autor);
        topicoRepository.save(topico);

        // Crear la URL de respuesta
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        // Crear el DTO de respuesta y retornarlo
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detallarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico);
        return ResponseEntity.ok(datosTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listarTopicos(@PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion,
                                                                    @RequestParam(required = false) String nombreCurso,
                                                                    @RequestParam(required = false) Integer anio) {
        Page<Topico> topicos;
        if (nombreCurso != null && anio != null) {
            topicos = topicoRepository.findByCursoAndFechaCreacionYear(nombreCurso, anio, paginacion);
        } else {
            topicos = topicoRepository.findAll(paginacion);
        }

        Page<DatosRespuestaTopico> datos = topicos.map(DatosRespuestaTopico::new);
        return ResponseEntity.ok(datos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}