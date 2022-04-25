package br.com.cwi.crescer.melevaai.service.interno;

import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.service.interno.PassageiroSituacaoService;
import br.com.cwi.crescer.melevaai.util.PassageiroFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class PassageiroSituacaoServiceTest {

    @Test
    @DisplayName("Deve lancar erro casso passageiro não esteja livre")
    void deveLancarErroCasoPassageiroNaoEstejaLivro() {
        PassageiroSituacaoService passageiroSituacaoService = new PassageiroSituacaoService();
        Passageiro passageiro = PassageiroFactory.builder().situacaoUsuario(SituacaoUsuario.OCUPADO).build();

        assertThrows(ResponseStatusException.class, () -> {
            passageiroSituacaoService.verificarLivre(passageiro);
        });
    }

    @Test
    @DisplayName("Deve lancar erro casso passageiro esteja inativo")
    void deveLancarErroCasoPassageiroEstejaInativo() {
        PassageiroSituacaoService passageiroSituacaoService = new PassageiroSituacaoService();
        Passageiro passageiro = PassageiroFactory.builder().situacaoUsuario(SituacaoUsuario.INATIVO).build();

        assertThrows(ResponseStatusException.class, () -> {
            passageiroSituacaoService.verificarAtivo(passageiro);
        });
    }
}