package com.example.jogosapi.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{
    public RecursoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
