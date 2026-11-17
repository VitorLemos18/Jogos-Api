package com.example.jogosapi.controller;

import com.example.jogosapi.dto.JogoDto;
import com.example.jogosapi.dto.JogoResponseDto;
import com.example.jogosapi.service.JogoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Tag(name = "Jogos", description = "Endpoints para gerenciamento dos jogos")
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

    @GetMapping("/{id}")
    public ResponseEntity<JogoResponseDto> buscarPorId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(jogoService.buscarJogoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogoResponseDto> atualizarJogo(@PathVariable UUID id, @RequestBody @Valid JogoDto jogoDto){
        JogoResponseDto atualizarJogo = jogoService.atualizar(id, jogoDto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizarJogo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JogoResponseDto> removerJogo(@PathVariable UUID id){
        jogoService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
