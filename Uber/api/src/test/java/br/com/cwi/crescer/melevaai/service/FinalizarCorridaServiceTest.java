package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.Corrida;
import br.com.cwi.crescer.melevaai.model.SituacaoCorrida;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import br.com.cwi.crescer.melevaai.service.interno.VerificarFinalizarCorridaService;
import br.com.cwi.crescer.melevaai.util.CorridaFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinalizarCorridaServiceTest {

    @InjectMocks
    private FinalizarCorridaService service;

    @Mock
    private CorridaRepository corridaRepository;

    @Mock
    private BuscarCorridaService buscarCorridaService;

    @Mock
    private VerificarFinalizarCorridaService verificarFinalizarCorridaService;

    @Captor
    private ArgumentCaptor<Corrida> corridaCaptor;

    @Test
    @DisplayName("Deve finalizar corrida e com sucesso")
    void deveFinalizarCorridaComSucesso() {
        Corrida corrida = CorridaFactory.getCorridaIniciada();

        when(buscarCorridaService.buscarPorId(1L))
                .thenReturn(corrida);

        service.finalizar(1L);

        verify(buscarCorridaService).buscarPorId(1L);
        verify(verificarFinalizarCorridaService).verificarFinalizarCorrida(corrida);
        verify(corridaRepository).save(corridaCaptor.capture());

        Corrida corridaInterna = corridaCaptor.getValue();
        assertEquals(new BigDecimal("760.0"), corridaInterna.getPassageiro().getCreditos());
        assertEquals(SituacaoUsuario.LIVRE, corridaInterna.getPassageiro().getSituacaoUsuario());
        assertEquals(new BigDecimal("240.0"), corridaInterna.getMotorista().getCreditos());
        assertEquals(SituacaoUsuario.LIVRE, corridaInterna.getMotorista().getSituacaoUsuario());
        assertEquals(SituacaoCorrida.FINALIZADA, corridaInterna.getSituacaoCorrida());

    }

    @Test
    @DisplayName("Deve lancar erro ao finalizar corrida quando passageiro nao possui saldo suficiente")
    void deveLancarErroAoFinalizarComPassageiroSemCreditos() {
        Corrida corrida = CorridaFactory.getCorridaIniciada();
        corrida.getPassageiro().setCreditos(BigDecimal.ZERO);

        when(buscarCorridaService.buscarPorId(1L))
                .thenReturn(corrida);

        assertThrows(ResponseStatusException.class, () -> {
            service.finalizar(1L);
        });

        verify(buscarCorridaService).buscarPorId(1L);
        verify(verificarFinalizarCorridaService).verificarFinalizarCorrida(corrida);



    }
}