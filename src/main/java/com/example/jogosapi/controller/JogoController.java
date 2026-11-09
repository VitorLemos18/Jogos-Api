package com.example.jogosapi.controller;

import com.example.jogosapi.dto.JogoDto;
import com.example.jogosapi.dto.JogoResponseDto;
import com.example.jogosapi.service.JogoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jogos")
@RequiredArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    @PostMapping
    public ResponseEntity<JogoResponseDto> cadastrar(@RequestBody @Valid JogoDto jogoDto,
                                                     @RequestParam UUID estudioId){
        JogoResponseDto novoJogo = jogoService.cadastrar(jogoDto, estudioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogo);

    }

    @GetMapping
    public ResponseEntity<List<JogoResponseDto>> listarTodosJogos(){
        List<JogoResponseDto> lista =  jogoService.listarTodosJogos();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

}
