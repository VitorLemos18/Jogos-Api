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

    @Transactional(readOnly = true)
    public List<EstudioResponseDto> listarTodosEstudios(){
        return estudioRepository.findAll()
                .stream()
                .map(EstudioResponseDto::new)
                .toList();
    }

}
