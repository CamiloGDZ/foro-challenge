package com.forohub.topico.repositorio;

import com.forohub.topico.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    Page<Topico> findByCursoAndFechaCreacionYear(String nombreCurso, int anio, Pageable paginacion);
}