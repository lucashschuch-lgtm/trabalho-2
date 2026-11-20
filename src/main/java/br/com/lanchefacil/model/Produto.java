package br.com.lanchefacil.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {
    private final String nome;
    private final BigDecimal preco;
    private boolean disponivel;

    public Produto(String nome, BigDecimal preco, boolean disponivel) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto e obrigatorio.");
        }
        Objects.requireNonNull(preco, "O preco do produto e obrigatorio.");
        if (preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preco nao pode ser negativo.");
        }

        this.nome = nome.trim();
        this.preco = preco;
        this.disponivel = disponivel;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
