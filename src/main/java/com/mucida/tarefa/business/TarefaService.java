package com.mucida.tarefa.business;

import com.mucida.tarefa.business.dto.TarefaDTO;
import com.mucida.tarefa.business.mapper.TarefaMapper;
import com.mucida.tarefa.business.mapper.TarefaUpdateMapper;
import com.mucida.tarefa.infrastructure.entity.Tarefa;
import com.mucida.tarefa.infrastructure.enums.StatusNotificacaoEnum;
import com.mucida.tarefa.infrastructure.exceptions.ResourceNotFoundException;
import com.mucida.tarefa.infrastructure.repository.TarefaRepository;
import com.mucida.tarefa.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateMapper tarefaUpdateMapper;

    public TarefaDTO saveTarefa(TarefaDTO tarefaDTO, String token) {

        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setEmailUsuario(email);
        tarefaDTO.setStatus(StatusNotificacaoEnum.PENDENTE);
        Tarefa tarefa = tarefaMapper.toTarefa(tarefaDTO);
        return tarefaMapper.toTarefaDTO(tarefaRepository.save(tarefa));
    }

    public List<TarefaDTO> findByDataEventoBetween(LocalDateTime firstDate, LocalDateTime finalDate) {
        return tarefaMapper.toTarefaDTOList(tarefaRepository.findByDataEventoBetween(firstDate, finalDate));

    }

    public List<TarefaDTO> findByEmailUsuario(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefaMapper.toTarefaDTOList(tarefaRepository.findByEmailUsuario(email));
    }

    public void deleteById(String id) {
        try {
            tarefaRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Id inexistente " + id, e.getCause());
        }
    }

    public TarefaDTO updateStatus(StatusNotificacaoEnum status, String id) {
        try {
            Tarefa tarefa = tarefaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefa.setStatus(status);
            return tarefaMapper.toTarefaDTO(tarefaRepository.save(tarefa));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar Status da Tarefa", e.getCause());
        }
    }

    public TarefaDTO updateTarefa(TarefaDTO tarefaDTO, String id) {
        try {
            Tarefa tarefa = tarefaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefaUpdateMapper.updateTarefa(tarefaDTO, tarefa);
            return tarefaMapper.toTarefaDTO(tarefaRepository.save(tarefa));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar Tarefa", e.getCause());
        }

    }
}
