package br.com.cwi.crescer.melevaai.service.interno;

import br.com.cwi.crescer.melevaai.model.Corrida;
import br.com.cwi.crescer.melevaai.service.interno.VerificarFinalizarCorridaService;
import br.com.cwi.crescer.melevaai.service.TimeService;
import br.com.cwi.crescer.melevaai.util.CorridaFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FinalizarCorridaSituacaoServiceTest {

    @InjectMocks
    private VerificarFinalizarCorridaService verificarFinalizarCorridaService;

    @Mock
    private TimeService timeService;

    @Test
    @DisplayName("Deve lançar erro caso corrida nao esteja iniciada")
    void deveLancarErroCassoCorridaNaoEstejaIniciada() {
        Corrida corrida = CorridaFactory.get();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            verificarFinalizarCorridaService.verificarFinalizarCorrida(corrida);
        });

        assertTrue(exception.getMessage().contains("Não é possível finalizar uma corrida que não está iniciada"));
    }

    @Test
    @DisplayName("Deve lançar erro caso a hora final da corrida ainda não tenha chegado")
    void deveLancarErroCasoHoraFinalNaoTenhaChegado() {
        Corrida corrida = CorridaFactory.getCorridaIniciada();

        Mockito.when(timeService.nowWithTime())
                        .thenReturn(corrida.getHoraInicio().plusSeconds(10));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            verificarFinalizarCorridaService.verificarFinalizarCorrida(corrida);
        });

        Mockito.verify(timeService).nowWithTime();
        assertTrue(exception.getMessage().contains("O motorista ainda não chegou no destino"));
    }
}