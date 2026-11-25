package com.example.jogosapi.database.repository;

import com.example.jogosapi.database.models.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendaRepository extends JpaRepository<Venda, UUID> {
    Page<Venda> findByUsuarioId(UUID usuarioId, Pageable paginacao);
}
