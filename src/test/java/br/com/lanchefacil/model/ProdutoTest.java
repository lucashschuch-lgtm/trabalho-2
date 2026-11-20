package br.com.lanchefacil.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void criaProduto() {
        Produto produto = new Produto("Tamagochi", BigDecimal.valueOf(10), true);

        assertEquals("Tamagochi", produto.getNome());
        assertEquals(BigDecimal.valueOf(10), produto.getPreco());
        assertTrue(produto.isDisponivel());
    }
}
