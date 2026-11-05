package com.example.jogosapi.database.models;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Table(name = "estudio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(nullable = false, name = "ano_fundacao")
    private Integer anoFundacao;




}
