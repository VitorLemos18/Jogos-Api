package com.example.jogosapi.database.repository;


import com.example.jogosapi.database.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);
}
