package br.com.lanchefacil.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemPedidoTest {

    @Test
    void deveCalcularSubtotalCorretamente() {

        // Preparar
        Produto produto = new Produto("Hambúrguer", BigDecimal.valueOf(10), true);
        ItemPedido itemPedido = new ItemPedido(produto, 3);

        // Executar
        BigDecimal subtotal = itemPedido.calcularSubtotal();

        // Verificar
        assertEquals(BigDecimal.valueOf(30), subtotal);
    }

    @Test
    void deveAumentarQuantidade() {

        // Preparar
        Produto produto = new Produto("Hambúrgue", BigDecimal.valueOf(10), true);
        ItemPedido item = new ItemPedido(produto, 3);

        // Executar
        item.setQuantidade(5);

        // Verificar
        assertEquals(5, item.getQuantidade());
        }

    @Test
    void deveDiminuirQuantidade() {

        // Preparar
        Produto produto = new Produto("Hambúrguer", BigDecimal.valueOf(10), true);
        ItemPedido item = new ItemPedido(produto, 5);

        // Executar
        item.setQuantidade(2);

        // Verificar
        assertEquals(2, item.getQuantidade());
    }

    @Test
    void naoDevePermitirQuantidadeZero() {

        // Preparar
        Produto produto = new Produto("Hambúrguer", BigDecimal.valueOf(10), true);
        ItemPedido item = new ItemPedido(produto, 3);

        // Executar e verificar
        assertThrows(IllegalArgumentException.class, () -> {
            item.setQuantidade(0);
        });
    }

    @Test
    void criaPedidoSemProdutos() {

        Pedido pedido = new Pedido();

        BigDecimal valorTotal = pedido.calcularTotal();


        assertEquals(BigDecimal.ZERO, valorTotal);
        assertEquals(StatusPedido.ABERTO, pedido.getStatus());
    }
}