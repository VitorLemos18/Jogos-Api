package com.example.jogosapi.dto;

import com.example.jogosapi.database.models.Usuario;

import java.time.LocalDate;
import java.util.UUID;

public record UsuarioResponseDto(
        UUID id,
        String nome,
        String email,
        String cpf,
        LocalDate dataNascimento
) {

    public UsuarioResponseDto(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getDataNascimento()
        );
    }

}
