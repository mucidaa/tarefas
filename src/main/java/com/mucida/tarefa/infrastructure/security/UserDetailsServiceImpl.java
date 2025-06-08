package com.mucida.tarefa.infrastructure.security;

import com.mucida.tarefa.business.dto.UsuarioDTO;
import com.mucida.tarefa.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {

    private final UsuarioClient usuarioClient;

    public UserDetails loadUserByUsername(String email, String token) {

        UsuarioDTO usuarioDTO = usuarioClient.findByEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();
    }
}