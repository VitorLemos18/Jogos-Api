package com.example.jogosapi.database.models;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "estudio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudio extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(name = "data_fundacao", nullable = false)
    private LocalDate dataFundacao;




}
