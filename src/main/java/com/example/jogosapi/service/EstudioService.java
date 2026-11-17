package com.example.jogosapi.service;


import com.example.jogosapi.database.models.Estudio;
import com.example.jogosapi.database.repository.EstudioRepository;
import com.example.jogosapi.dto.EstudioDto;
import com.example.jogosapi.dto.EstudioResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstudioService {

    private final EstudioRepository estudioRepository;

    @Transactional(readOnly = true)
    public EstudioResponseDto buscarPorId(UUID id){
        Estudio estudio = estudioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudio não encontrado com esse ID: " + id));

        return new EstudioResponseDto(estudio);
    }

    @Transactional()
    public EstudioResponseDto cadastrar(EstudioDto estudioDto) {
        boolean estudioExiste = estudioRepository.existsByNome(estudioDto.nome());
        if (estudioExiste) {
            throw new RuntimeException("Estudio já cadastrado com esse nome: " + estudioDto.nome());
        }

        Estudio estudio = new Estudio();
        estudio.setNome(estudioDto.nome());
        estudio.setDescricao(estudioDto.descricao());
        estudio.setDataFundacao(estudioDto.dataFundacao());

        Estudio estudioSalvo = estudioRepository.save(estudio);

        return new EstudioResponseDto(estudioSalvo);
    }

    @Transactional
    public EstudioResponseDto atualizar(UUID id,EstudioDto estudioDto) {
        Estudio estudio = estudioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estúdio não encontrado com o ID:" + id));

        if (!estudio.getNome().equalsIgnoreCase(estudioDto.nome())
                && estudioRepository.existsByNome(estudioDto.nome())) {

            throw new RuntimeException("Já existe outro estúdio cadastrado com o nome: " + estudioDto.nome());
        }
        estudio.setNome(estudioDto.nome());
        estudio.setDescricao(estudioDto.descricao());
        estudio.setDataFundacao(estudioDto.dataFundacao());
        estudioRepository.save(estudio);
        return new EstudioResponseDto(estudio);

    }

    @Transactional(readOnly = true)
    public List<EstudioResponseDto> listarTodosEstudios(){
        return estudioRepository.findAll()
                .stream()
                .map(EstudioResponseDto::new)
                .toList();
    }


    @Transactional
    public void remover(UUID id){
        Estudio estudio = estudioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudio não encontrado com o ID:" + id));
        estudio.SoftDelete();
        estudioRepository.save(estudio);


    }


}
