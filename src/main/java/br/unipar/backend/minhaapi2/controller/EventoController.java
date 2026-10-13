package br.unipar.backend.minhaapi2.controller;

import br.unipar.backend.minhaapi2.controller.model.Evento;
import br.unipar.backend.minhaapi2.repository.EventoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evento")
public class EventoController {

    private final EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Evento>> listarTodos() {
        return ResponseEntity.ok(eventoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {
        Optional<Evento> eventoEncontrado = eventoRepository.findById(id);

        if (eventoEncontrado.isPresent()) {
            return ResponseEntity.ok(eventoEncontrado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/gravar")
    public ResponseEntity<Evento> gravarEvento(@RequestBody Evento evento) {
        evento.setId(null);
        Evento eventoSalvo = eventoRepository.save(evento);

        return ResponseEntity.ok(eventoSalvo);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Evento> editarEvento(@PathVariable Long id,
                                                @RequestBody Evento eventoAtualizado) {
        Optional<Evento> eventoEncontrado = eventoRepository.findById(id);

        if (eventoEncontrado.isPresent()) {
            Evento evento = eventoEncontrado.get();

            evento.setTitulo(eventoAtualizado.getTitulo());
            evento.setDescricao(eventoAtualizado.getDescricao());
            evento.setData(eventoAtualizado.getData());
            evento.setHorario(eventoAtualizado.getHorario());
            evento.setCategoria(eventoAtualizado.getCategoria());

            Evento eventoSalvo = eventoRepository.save(evento);
            return ResponseEntity.ok(eventoSalvo);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEvento(@PathVariable Long id) {
        Optional<Evento> eventoEncontrado = eventoRepository.findById(id);

        if (eventoEncontrado.isPresent()) {
            eventoRepository.delete(eventoEncontrado.get());
            return ResponseEntity.ok("Evento " + id + " deletado com sucesso");
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Evento>> filtrarEventos(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String data,
            @RequestParam(required = false) String categoria) {

        List<Evento> eventosFiltrados = eventoRepository.filtrar(
                titulo, data, categoria);
        return ResponseEntity.ok(eventosFiltrados);
    }
}
