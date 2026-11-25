package com.example.jogosapi.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Table(name = "item_vendas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco_unitario;

    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;
    @ManyToOne
    @JoinColumn(name = "jogo_id", nullable = false)
    private Jogo jogo;
}
