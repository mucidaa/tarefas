package com.mucida.tarefa.controller;

import com.mucida.tarefa.business.TarefaService;
import com.mucida.tarefa.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> saveTarefa(@RequestBody TarefaDTO tarefaDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.saveTarefa(tarefaDTO, token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> findByDataEventoBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime firstDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate) {
        return ResponseEntity.ok(tarefaService.findByDataEventoBetween(firstDate, finalDate));
    }

    @GetMapping()
    public ResponseEntity<List<TarefaDTO>> findByEmailUsuario(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.findByEmailUsuario(token));
    }

}
