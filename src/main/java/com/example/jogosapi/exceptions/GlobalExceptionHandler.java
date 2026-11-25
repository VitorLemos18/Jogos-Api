package com.example.jogosapi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> tratarRuntimeExcpetion(RuntimeException ex){

        var erro = new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarRuntimeExcpetion(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors().stream()
                .map(DadosErroValidacao::new)
                .toList();

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponseDto> tratarRecursoNaoEcontrado(RecursoNaoEncontradoException ex){
        var erro = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
