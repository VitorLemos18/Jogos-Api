package com.example.jogosapi.service;


import com.example.jogosapi.database.models.Estudio;
import com.example.jogosapi.database.models.Jogo;
import com.example.jogosapi.database.repository.EstudioRepository;
import com.example.jogosapi.database.repository.JogoRepository;
import com.example.jogosapi.dto.JogoDto;
import com.example.jogosapi.dto.JogoResponseDto;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JogoService {

    private final JogoRepository jogoRepository;
    private final EstudioRepository estudioRepository;

    @Transactional
    public JogoResponseDto cadastrar(JogoDto jogoDto, UUID estudioId) {
        Estudio estudio = estudioRepository.findById(estudioId)
                .orElseThrow(() -> new RuntimeException("Estudio Não encontrado com o ID: " + estudioId));

        boolean jogoExiste = jogoRepository.existsByTitulo(jogoDto.titulo());
        if (jogoExiste) {
            throw new RuntimeException("Jogo já cadastrado com esse nome: " + jogoDto.titulo());
        }

        Jogo jogo = new Jogo();
        jogo.setTitulo(jogoDto.titulo());
        jogo.setDescricao(jogoDto.descricao());
        jogo.setQuantidade(jogoDto.quantidade());
        jogo.setPreco(jogoDto.preco());
        jogo.setDataLancamento(jogoDto.dataLancamento());
        jogo.setEstudio(estudio);

        Jogo jogoSalvo = jogoRepository.save(jogo);

        return new JogoResponseDto(jogoSalvo);
    }
    @Transactional(readOnly = true)
    public List<JogoResponseDto> listarTodosJogos(){
        return jogoRepository.findAll()
                .stream()
                .map(JogoResponseDto::new)
                .toList();
    }
}
