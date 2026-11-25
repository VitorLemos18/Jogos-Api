package com.example.jogosapi.controller;


import com.example.jogosapi.dto.UsuarioDto;
import com.example.jogosapi.dto.UsuarioResponseDto;
import com.example.jogosapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios" , description = "Endpoints para gerenciamento de usuarios")
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Cadastrar usuário", description = "Cria uma nova conta de usuário com perfil padrão USER (rota pública)")
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto)
    {
        UsuarioResponseDto novoUsuario = usuarioService.cadastrarUsuario(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @Operation(summary = "Listar usuários", description = "Retorna todos os usuários cadastrados. Requer perfil ADMIN")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios()
    {
        List<UsuarioResponseDto> lista = usuarioService.listarUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }


}
