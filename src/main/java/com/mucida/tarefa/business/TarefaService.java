package com.mucida.tarefa.business;

import com.mucida.tarefa.business.dto.TarefaDTO;
import com.mucida.tarefa.business.mapper.TarefaMapper;
import com.mucida.tarefa.infrastructure.entity.Tarefa;
import com.mucida.tarefa.infrastructure.enums.StatusNotificacaoEnum;
import com.mucida.tarefa.infrastructure.repository.TarefaRepository;
import com.mucida.tarefa.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final JwtUtil jwtUtil;

    public TarefaDTO saveTarefa(TarefaDTO tarefaDTO, String token) {

        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setEmailUsuario(email);
        tarefaDTO.setStatus(StatusNotificacaoEnum.PENDENTE);
        Tarefa tarefa = tarefaMapper.toTarefa(tarefaDTO);
        return tarefaMapper.toTarefaDTO(tarefaRepository.save(tarefa));
    }

}
