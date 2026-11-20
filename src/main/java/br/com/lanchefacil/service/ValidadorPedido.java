package br.com.lanchefacil.service;

import br.com.lanchefacil.model.ItemPedido;
import br.com.lanchefacil.model.Pedido;

import java.util.Objects;

public final class ValidadorPedido {
    private ValidadorPedido() {
    }

    public static void validar(Pedido pedido) {
        Objects.requireNonNull(pedido, "O pedido e obrigatorio.");

        if (pedido.getItens().isEmpty()) {
            throw new IllegalStateException("O pedido precisa ter pelo menos um item.");
        }

        for (ItemPedido item : pedido.getItens()) {
            if (!item.getProduto().isDisponivel()) {
                throw new IllegalStateException(
                        "O pedido contem produto indisponivel: " + item.getProduto().getNome()
                );
            }
        }
    }
}
