package com.example.jogosapi.database.models;


import com.example.jogosapi.dto.ItemVendaResponseDto;
import com.example.jogosapi.enums.StatusRole;
import com.example.jogosapi.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vendas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venda extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "data_compra",nullable = false)
    private LocalDate dataCompra;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor_total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusRole status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> itens = new ArrayList<>();

}


