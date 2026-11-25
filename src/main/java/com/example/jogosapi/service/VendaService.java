package com.example.jogosapi.service;

import com.example.jogosapi.database.models.ItemVenda;
import com.example.jogosapi.database.models.Jogo;
import com.example.jogosapi.database.models.Usuario;
import com.example.jogosapi.database.models.Venda;
import com.example.jogosapi.database.repository.JogoRepository;
import com.example.jogosapi.database.repository.UsuarioRepository;
import com.example.jogosapi.database.repository.VendaRepository;
import com.example.jogosapi.dto.ItemVendaDto;
import com.example.jogosapi.dto.VendaDto;
import com.example.jogosapi.dto.VendaResponseDto;
import com.example.jogosapi.enums.StatusRole;
import com.example.jogosapi.exceptions.RecursoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final UsuarioRepository usuarioRepository;
    private final JogoRepository jogoRepository;

    @Transactional
    public VendaResponseDto realizarVenda(VendaDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com o ID: " + dto.usuarioId()));

        Venda venda = new Venda();
        venda.setUsuario(usuario);
        venda.setDataCompra(LocalDate.now());
        venda.setStatus(StatusRole.PAGA);

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemVendaDto itemDto : dto.itens()) {
            Jogo jogo = jogoRepository.findById(itemDto.jogoId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Jogo não encontrado com o ID: " + itemDto.jogoId()));

            if (jogo.getQuantidade() < itemDto.quantidade()) {
                throw new RuntimeException("Estoque insuficiente para o jogo: " + jogo.getTitulo()
                        + ". Disponível: " + jogo.getQuantidade());
            }

            jogo.setQuantidade(jogo.getQuantidade() - itemDto.quantidade());
            jogoRepository.save(jogo);

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(venda);
            itemVenda.setJogo(jogo);
            itemVenda.setQuantidade(itemDto.quantidade());
            itemVenda.setPreco_unitario(jogo.getPreco());

            venda.getItens().add(itemVenda);

            BigDecimal subtotal = jogo.getPreco().multiply(BigDecimal.valueOf(itemDto.quantidade()));
            valorTotal = valorTotal.add(subtotal);
        }

        venda.setValor_total(valorTotal);
        Venda vendaSalva = vendaRepository.save(venda);

        return new VendaResponseDto(vendaSalva);
    }

    public Page<VendaResponseDto> listarTodas(Pageable paginacao) {
        return vendaRepository.findAll(paginacao).map(VendaResponseDto::new);
    }

    public Page<VendaResponseDto> listarPorUsuario(UUID usuarioId, Pageable paginacao) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado com o ID: " + usuarioId);
        }
        return vendaRepository.findByUsuarioId(usuarioId, paginacao).map(VendaResponseDto::new);
    }

    public VendaResponseDto buscarPorId(UUID id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Venda não encontrada com o ID: " + id));
        return new VendaResponseDto(venda);
    }
}
