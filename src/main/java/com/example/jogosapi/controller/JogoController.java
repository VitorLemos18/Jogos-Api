package com.example.jogosapi.controller;

import com.example.jogosapi.dto.JogoDto;
import com.example.jogosapi.dto.JogoResponseDto;
import com.example.jogosapi.service.JogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Tag(name = "Jogos", description = "Endpoints para gerenciamento dos jogos")
@RestController
@RequestMapping("/jogos")
@RequiredArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    @Operation(summary = "Cadastrar jogo", description = "Cadastra um novo jogo vinculado a um estúdio. Requer perfil ADMIN")
    @PostMapping
    public ResponseEntity<JogoResponseDto> cadastrar(@RequestBody @Valid JogoDto jogoDto,
                                                     @RequestParam UUID estudioId){
        JogoResponseDto novoJogo = jogoService.cadastrar(jogoDto, estudioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogo);

    }

    @Operation(summary = "Listar jogos", description = "Retorna todos os jogos ativos de forma paginada (rota pública)")
    @GetMapping
    public ResponseEntity<Page<JogoResponseDto>> listarTodosJogos(
            @PageableDefault(size = 10, sort = "titulo", direction = Sort.Direction.ASC)Pageable paginacao
            ){
        Page<JogoResponseDto> pagina = jogoService.listarTodosOsJogos(paginacao);
        return ResponseEntity.status(HttpStatus.OK).body(pagina);
    }

    @Operation(summary = "Buscar jogo por ID", description = "Retorna os detalhes de um jogo específico (rota pública)")
    @GetMapping("/{id}")
    public ResponseEntity<JogoResponseDto> buscarPorId(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(jogoService.buscarJogoPorId(id));
    }

    @Operation(summary = "Buscar jogos por estúdio", description = "Lista os jogos de um estúdio específico de forma paginada (rota pública)")
    @GetMapping("/estudio/{estudioId}")
    public ResponseEntity<Page<JogoResponseDto>> buscarPorEstudio(@PathVariable UUID estudioId, @PageableDefault(size = 10,
            sort = "titulo", direction = Sort.Direction.ASC)Pageable paginacao){
        Page<JogoResponseDto> jogos = jogoService.buscarPorEstudioId(estudioId, paginacao);
        return ResponseEntity.status(HttpStatus.OK).body(jogos);
    }

    @Operation(summary = "Buscar jogos por título", description = "Pesquisa jogos pelo título ignorando maiúsculas e minúsculas (rota pública)")
    @GetMapping("/busca")
    public ResponseEntity<Page<JogoResponseDto>> buscarPorTitulo(
            @RequestParam String titulo,
            @PageableDefault(size = 10, sort = "titulo", direction = Sort.Direction.ASC)Pageable paginacao
    ){
        Page<JogoResponseDto> jogos = jogoService.buscarPorTitulo(titulo, paginacao);
        return ResponseEntity.status(HttpStatus.OK).body(jogos);
    }

    @Operation(summary = "Atualizar jogo", description = "Atualiza os dados de um jogo existente. Requer perfil ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<JogoResponseDto> atualizarJogo(@PathVariable UUID id, @RequestBody @Valid JogoDto jogoDto){
        JogoResponseDto atualizarJogo = jogoService.atualizar(id, jogoDto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizarJogo);
    }

    @Operation(summary = "Remover jogo", description = "Realiza a exclusão lógica (soft delete) de um jogo. Requer perfil ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<JogoResponseDto> removerJogo(@PathVariable UUID id){
        jogoService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
