package br.com.cwi.crescer.melevaai.util;

import br.com.cwi.crescer.melevaai.model.*;

import java.time.LocalDateTime;

public class CorridaFactory {
    public static Corrida getCorridaSemId(){
        return builderSemId().build();
    }

    public static Corrida get(){
        return builderSemId()
                .id(1L)
                .build();
    }

    public static Corrida getCorridaIniciada(){
        Corrida corrida = builderSemId()
                .id(1L)
                .situacaoCorrida(SituacaoCorrida.INICIADA)
                .horaInicio(LocalDateTime.parse("2022-01-01T10:00:00"))
                .horaFinal(LocalDateTime.parse("2022-01-01T10:00:00").plusSeconds(1200L))
                .build();

        corrida.getMotorista().setSituacaoUsuario(SituacaoUsuario.OCUPADO);
        corrida.getPassageiro().setSituacaoUsuario(SituacaoUsuario.OCUPADO);

        return corrida;
    }

    public static Corrida.CorridaBuilder builderSemId(){
        return Corrida.builder()
                .distancia(10D)
                .motorista(MotoristaFactory.get())
                .passageiro(PassageiroFactory.get())
                .situacaoCorrida(SituacaoCorrida.SOLICITADA);
    }
}
