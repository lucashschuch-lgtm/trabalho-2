package br.com.lanchefacil.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest2 {

    @Test
    void deveLancarExcecaoQuandoPrecoForNegativo() {

        // Executar e Verificar
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Produto Teste", BigDecimal.valueOf(-1), true);
        });
    }
}