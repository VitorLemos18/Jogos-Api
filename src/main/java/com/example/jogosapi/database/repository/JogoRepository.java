package com.example.jogosapi.database.repository;


import com.example.jogosapi.database.models.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, UUID> {
    boolean existsByTitulo(String titulo);
}
