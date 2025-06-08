package com.mucida.tarefa.business.mapper;

import com.mucida.tarefa.business.dto.TarefaDTO;
import com.mucida.tarefa.infrastructure.entity.Tarefa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    Tarefa toTarefa(TarefaDTO tarefaDTO);
    TarefaDTO toTarefaDTO(Tarefa tarefa);
    List<Tarefa> toTarefaList(List<TarefaDTO> tarefaDTOList);
    List<TarefaDTO> toTarefaDTOList(List<Tarefa> tarefaList);

}
