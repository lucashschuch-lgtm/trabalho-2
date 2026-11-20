package br.com.lanchefacil.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class CalculadoraDesconto {
    private static final BigDecimal LIMITE_DESCONTO_10 = new BigDecimal("100.00");
    private static final BigDecimal LIMITE_DESCONTO_15 = new BigDecimal("200.00");
    private static final BigDecimal DEZ_POR_CENTO = new BigDecimal("0.10");
    private static final BigDecimal QUINZE_POR_CENTO = new BigDecimal("0.15");

    public BigDecimal obterPercentualDesconto(BigDecimal valorPedido) {
        validarValor(valorPedido);

        if (valorPedido.compareTo(LIMITE_DESCONTO_15) > 0) {
            return QUINZE_POR_CENTO;
        }
        if (valorPedido.compareTo(LIMITE_DESCONTO_10) > 0) {
            return DEZ_POR_CENTO;
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal calcularValorFinal(BigDecimal valorPedido) {
        BigDecimal percentual = obterPercentualDesconto(valorPedido);
        return aplicarDesconto(valorPedido, percentual);
    }

    public BigDecimal aplicarDesconto(BigDecimal valor, BigDecimal percentual) {
        validarValor(valor);
        Objects.requireNonNull(percentual, "O percentual e obrigatorio.");

        if (percentual.compareTo(BigDecimal.ZERO) < 0
                || percentual.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("O percentual deve estar entre 0 e 1.");
        }

        BigDecimal fator = BigDecimal.ONE.subtract(percentual);
        return valor.multiply(fator).setScale(2, RoundingMode.HALF_UP);
    }

    private void validarValor(BigDecimal valor) {
        Objects.requireNonNull(valor, "O valor do pedido e obrigatorio.");
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor do pedido nao pode ser negativo.");
        }
    }
}
