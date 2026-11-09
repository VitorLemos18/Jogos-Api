package com.example.jogosapi.controller;


import com.example.jogosapi.dto.EstudioDto;
import com.example.jogosapi.dto.EstudioResponseDto;
import com.example.jogosapi.service.EstudioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudios")
@RequiredArgsConstructor
public class EstudioController {

    private final EstudioService estudioService;

    @PostMapping
    public ResponseEntity<EstudioResponseDto> cadastrar(@RequestBody @Valid EstudioDto estudioDto){
        EstudioResponseDto novoEstudio = estudioService.cadastrar(estudioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEstudio);
    }

    @GetMapping
    public ResponseEntity<List<EstudioResponseDto>> listarTodosEstudios(){
        List<EstudioResponseDto> lista = estudioService.listarTodosEstudios();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }
}
