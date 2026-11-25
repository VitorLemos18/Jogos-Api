package com.example.jogosapi.database.repository;

import com.example.jogosapi.database.models.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, UUID> {
}
