package com.mucida.tarefa.infrastructure.repository;

import com.mucida.tarefa.infrastructure.entity.Tarefa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<Tarefa, String> {

    List<Tarefa> findByDataEventoBetween(LocalDateTime firstDate, LocalDateTime finalDate);

    List<Tarefa> findByEmailUsuario(String email);
}
