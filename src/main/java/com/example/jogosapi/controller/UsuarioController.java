package com.example.jogosapi.controller;


import com.example.jogosapi.dto.UsuarioDto;
import com.example.jogosapi.dto.UsuarioResponseDto;
import com.example.jogosapi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto)
    {
        UsuarioResponseDto novoUsuario = usuarioService.cadastrarUsuario(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios()
    {
        List<UsuarioResponseDto> lista = usuarioService.listarUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }


}
