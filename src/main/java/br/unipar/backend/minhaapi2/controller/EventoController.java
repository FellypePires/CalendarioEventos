package br.unipar.backend.minhaapi2.controller;

import br.unipar.backend.minhaapi2.controller.model.Evento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {

    List<Evento> bancoDados = new ArrayList<>();
    Long proximoId = 1L;

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Evento>> listarTodos() {
        return ResponseEntity.ok(bancoDados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {
        for (Evento evento : bancoDados) {
            if (evento.getId().equals(id)) {
                return ResponseEntity.ok(evento);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/gravar")
    public ResponseEntity<Evento> gravarEvento(@RequestBody Evento evento) {
        evento.setId(proximoId);
        proximoId++;

        bancoDados.add(evento);

        return ResponseEntity.ok(evento);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Evento> editarEvento(@PathVariable Long id,
                                                @RequestBody Evento eventoAtualizado) {
        for (Evento evento : bancoDados) {
            if (evento.getId().equals(id)) {
                evento.setTitulo(eventoAtualizado.getTitulo());
                evento.setDescricao(eventoAtualizado.getDescricao());
                evento.setData(eventoAtualizado.getData());
                evento.setHorario(eventoAtualizado.getHorario());
                evento.setCategoria(eventoAtualizado.getCategoria());

                return ResponseEntity.ok(evento);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEvento(@PathVariable Long id) {
        for (int i = 0; i < bancoDados.size(); i++) {
            Evento evento = bancoDados.get(i);

            if (evento.getId().equals(id)) {
                bancoDados.remove(i);
                return ResponseEntity.ok("Evento " + id + " deletado com sucesso");
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Evento>> filtrarEventos(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String data,
            @RequestParam(required = false) String categoria) {

        List<Evento> eventosFiltrados = new ArrayList<>();

        for (Evento evento : bancoDados) {
            boolean adicionarEvento = true;

            if (titulo != null &&
                    !evento.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                adicionarEvento = false;
            }

            if (data != null && !evento.getData().equals(data)) {
                adicionarEvento = false;
            }

            if (categoria != null &&
                    !evento.getCategoria().equalsIgnoreCase(categoria)) {
                adicionarEvento = false;
            }

            if (adicionarEvento) {
                eventosFiltrados.add(evento);
            }
        }

        return ResponseEntity.ok(eventosFiltrados);
    }
}
