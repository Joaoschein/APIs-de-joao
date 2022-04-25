package br.com.cwi.crescer.melevaai.util;

import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PassageiroFactory {
    public static Passageiro get(){
        return builder().build();
    }

    public static Passageiro.PassageiroBuilder builder(){
        return Passageiro.builder()
                .id(1L)
                .creditos(new BigDecimal("1000.0"))
                .avaliacao(new BigDecimal("3"))
                .CPF("99911199900")
                .nome("Passageiro Teste")
                .situacaoUsuario(SituacaoUsuario.LIVRE)
                .dataNascimento(LocalDate.parse("1990-01-01"));
    }
}
