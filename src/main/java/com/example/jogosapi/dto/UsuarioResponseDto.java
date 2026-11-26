package com.example.jogosapi.dto;

import com.example.jogosapi.database.models.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Dados públicos retornados de um usuário")
public record UsuarioResponseDto(
        @Schema(description = "ID único do usuário")
        UUID id,
        @Schema(description = "Nome do usuário", example = "Vitor Lemos")
        String nome,
        @Schema(description = "Email do usuário", example = "vitor@email.com")
        String email,
        @Schema(description = "CPF do usuário", example = "11144477735")
        String cpf,
        @Schema(description = "Data de nascimento", example = "2000-05-15")
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
