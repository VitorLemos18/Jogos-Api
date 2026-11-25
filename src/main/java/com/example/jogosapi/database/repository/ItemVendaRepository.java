package com.example.jogosapi.database.repository;

import com.example.jogosapi.database.models.ItemVenda;
import com.example.jogosapi.database.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Integer> {
}
