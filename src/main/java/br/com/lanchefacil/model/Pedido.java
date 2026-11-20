package br.com.lanchefacil.model;

import br.com.lanchefacil.service.ValidadorPedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Pedido {
    private final List<ItemPedido> itens = new ArrayList<>();
    private StatusPedido status = StatusPedido.ABERTO;

    public void adicionarItem(Produto produto, int quantidade) {
        Objects.requireNonNull(produto, "O produto e obrigatorio.");
        if (!produto.isDisponivel()) {
            throw new IllegalArgumentException("Nao e possivel adicionar um produto indisponivel.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        ItemPedido existente = buscarItem(produto);
        if (existente != null) {
            existente.setQuantidade(existente.getQuantidade() + quantidade);
        } else {
            itens.add(new ItemPedido(produto, quantidade));
        }
    }

    public void removerItem(Produto produto) {
        Objects.requireNonNull(produto, "O produto e obrigatorio.");
        boolean removido = itens.removeIf(item -> item.getProduto().equals(produto));
        if (!removido) {
            throw new IllegalArgumentException("Produto nao encontrado no pedido.");
        }
    }

    public ItemPedido buscarItem(Produto produto) {
        return itens.stream()
                .filter(item -> item.getProduto().equals(produto))
                .findFirst()
                .orElse(null);
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public int getQuantidadeItens() {
        return itens.size();
    }

    public BigDecimal calcularTotal() {
        return itens.stream()
                .map(ItemPedido::calcularSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void finalizar() {
        ValidadorPedido.validar(this);
        alterarStatus(StatusPedido.EM_PREPARACAO);
    }

    public void alterarStatus(StatusPedido novoStatus) {
        Objects.requireNonNull(novoStatus, "O novo status e obrigatorio.");

        boolean permitido = switch (status) {
            case ABERTO -> novoStatus == StatusPedido.EM_PREPARACAO
                    || novoStatus == StatusPedido.CANCELADO;
            case EM_PREPARACAO -> novoStatus == StatusPedido.PRONTO
                    || novoStatus == StatusPedido.CANCELADO;
            case PRONTO -> novoStatus == StatusPedido.ENTREGUE;
            case ENTREGUE, CANCELADO -> false;
        };

        if (!permitido) {
            throw new IllegalStateException(
                    "Transicao de status invalida: " + status + " -> " + novoStatus
            );
        }

        status = novoStatus;
    }
}
