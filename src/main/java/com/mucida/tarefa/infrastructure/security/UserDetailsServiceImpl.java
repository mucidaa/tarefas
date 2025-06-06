package com.mucida.tarefa.infrastructure.security;

import com.mucida.tarefa.business.dto.UsuarioDTO;
import com.mucida.tarefa.infrastructure.client.UsuarioClient;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    private final UsuarioClient usuarioClient;

    public UserDetailsServiceImpl(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    public UserDetails loadUserByUsernameToken(String email, String token) {

        UsuarioDTO usuarioDTO = usuarioClient.findByEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();
    }
}