package com.mucida.tarefa.business.mapper;

import com.mucida.tarefa.business.dto.TarefaDTO;
import com.mucida.tarefa.infrastructure.entity.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateMapper {

    void updateTarefa(TarefaDTO tarefaDTO, @MappingTarget Tarefa tarefa);
}
