package br.com.cwi.crescer.melevaai.service.interno;

import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.service.interno.MotoristaSituacaoService;
import br.com.cwi.crescer.melevaai.util.MotoristaFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

class MotoristaSituacaoServiceTest {

    @Test
    @DisplayName("Deve lancar erro caso motorista não esteja livre")
    void deveLancarErroCasoMotoristaNaoEstejaLivre() {
        MotoristaSituacaoService motoristaSituacaoService = new MotoristaSituacaoService();
        Motorista motorista = MotoristaFactory.builder().situacaoUsuario(SituacaoUsuario.OCUPADO).build();

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            motoristaSituacaoService.verificarLivre(motorista);
        });
    }

    @Test
    @DisplayName("Deve lancar erro caso motorista esteja inativo")
    void deveLancarErroCasoMotoristaEstejaInativo() {
        MotoristaSituacaoService motoristaSituacaoService = new MotoristaSituacaoService();
        Motorista motorista = MotoristaFactory.builder().situacaoUsuario(SituacaoUsuario.INATIVO).build();

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            motoristaSituacaoService.verificarAtivo(motorista);
        });
    }

}