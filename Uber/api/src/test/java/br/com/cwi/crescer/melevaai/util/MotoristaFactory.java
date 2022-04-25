package br.com.cwi.crescer.melevaai.util;

import br.com.cwi.crescer.melevaai.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class MotoristaFactory {
    public static Motorista get(){
        Motorista motorista = builder().build();
        return motorista;
    }

    public static Motorista getMotoristaComCorrida(){
        return builder()
                .corridas(Arrays.asList(
                        CorridaFactory.builderSemId().id(1L).situacaoCorrida(SituacaoCorrida.FINALIZADA).build(),
                        CorridaFactory.builderSemId().id(2L).build()
                ))
                .situacaoUsuario(SituacaoUsuario.OCUPADO)
                .build();
    }

    public static Motorista.MotoristaBuilder builder(){
        Motorista.MotoristaBuilder motorista = Motorista.builder()
                .id(1L)
                .avaliacao(new BigDecimal("3"))
                .creditos(BigDecimal.ZERO)
                .CPF("00011100011")
                .nome("Nome Teste")
                .situacaoUsuario(SituacaoUsuario.LIVRE)
                .dataNascimento(LocalDate.parse("2000-01-01"));

        motorista.veiculo(getVeiculo(motorista));
        return motorista;
    }



    private static Veiculo getVeiculo(Motorista.MotoristaBuilder motorista){
        return Veiculo.builder()
                .id(1L)
                .motorista(motorista.build())
                .categoria(Categoria.B)
                .cor("Laranja")
                .foto("www.url.com/exemplo")
                .modelo("Modelo Teste")
                .placa("TST0000")
                .build();
    }

}
