package br.unipar.backend.minhaapi2.repository;

import br.unipar.backend.minhaapi2.controller.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("""
            SELECT evento FROM Evento evento
            WHERE (:titulo IS NULL OR
                   LOWER(evento.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
              AND (:data IS NULL OR evento.data = :data)
              AND (:categoria IS NULL OR
                   LOWER(evento.categoria) = LOWER(:categoria))
            """)
    List<Evento> filtrar(@Param("titulo") String titulo,
                         @Param("data") String data,
                         @Param("categoria") String categoria);
}
