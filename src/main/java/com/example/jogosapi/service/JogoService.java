package com.example.jogosapi.service;


import com.example.jogosapi.database.models.Estudio;
import com.example.jogosapi.database.models.Jogo;
import com.example.jogosapi.database.repository.EstudioRepository;
import com.example.jogosapi.database.repository.JogoRepository;
import com.example.jogosapi.dto.JogoDto;
import com.example.jogosapi.dto.JogoResponseDto;
import com.example.jogosapi.exceptions.RecursoNaoEncontradoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estudio Não encontrado com o ID: " + estudioId));

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
    public Page<JogoResponseDto> listarTodosOsJogos(Pageable pageable) {
        return jogoRepository.findAll(pageable).map(JogoResponseDto::new);
    }

    @Transactional(readOnly = true)
    public JogoResponseDto buscarJogoPorId(UUID id){
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Jogo não encontrado com o ID: " + id));
        return new JogoResponseDto(jogo);
    }

    @Transactional
    public JogoResponseDto atualizar(UUID id,  JogoDto jogoDto) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado com o ID: " + id));

        if(!jogo.getTitulo().equals(jogoDto.titulo())
            && jogoRepository.existsByTitulo(jogoDto.titulo())) {

            throw new RuntimeException("Já existe outro jogo com esse nome: " + jogoDto.titulo());
        }

        jogo.setTitulo(jogoDto.titulo());
        jogo.setDescricao(jogoDto.descricao());
        jogo.setQuantidade(jogoDto.quantidade());
        jogo.setPreco(jogoDto.preco());
        jogo.setDataLancamento(jogoDto.dataLancamento());
        jogoRepository.save(jogo);
        return new JogoResponseDto(jogo);
    }

    @Transactional
    public void remover(UUID id){
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Jogo não encontrado com o ID: " + id));
        jogo.SoftDelete();
        jogoRepository.delete(jogo);
    }

    @Transactional(readOnly = true)
    public Page<JogoResponseDto> buscarPorTitulo(String titulo, Pageable paginacao) {
        return jogoRepository.findByTituloContainingIgnoreCase(titulo, paginacao).map(JogoResponseDto::new);
    }

    @Transactional(readOnly = true)
    public Page<JogoResponseDto> buscarPorEstudioId(UUID id, Pageable paginacao) {
        if(!estudioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Estudio não econtrado com esse id: " + id);
        }
        return jogoRepository.findByEstudioId(id,paginacao).map(JogoResponseDto::new);
    }
}
