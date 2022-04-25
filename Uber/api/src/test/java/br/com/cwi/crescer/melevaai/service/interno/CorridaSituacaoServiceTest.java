package br.com.cwi.crescer.melevaai.service.interno;

import br.com.cwi.crescer.melevaai.model.Corrida;
import br.com.cwi.crescer.melevaai.service.interno.CorridaSituacaoService;
import br.com.cwi.crescer.melevaai.util.CorridaFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

class CorridaSituacaoServiceTest {

    @Test
    @DisplayName("Deve lancar erro casso corrida nao esteja solicitada")
    void validarSolicitada() {
        CorridaSituacaoService corridaSituacaoService = new CorridaSituacaoService();
        Corrida corrida = CorridaFactory.getCorridaIniciada();

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            corridaSituacaoService.verificarSolicitada(corrida);
        });
    }
}