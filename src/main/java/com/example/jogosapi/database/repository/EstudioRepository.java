package com.example.jogosapi.database.repository;


import com.example.jogosapi.database.models.Estudio;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface EstudioRepository extends JpaRepository<Estudio, UUID> {
    boolean existsByNome(String nome);
}
