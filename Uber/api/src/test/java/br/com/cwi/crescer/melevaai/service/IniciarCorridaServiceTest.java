package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.IniciarCorridaResponse;
import br.com.cwi.crescer.melevaai.model.Corrida;
import br.com.cwi.crescer.melevaai.model.SituacaoCorrida;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import br.com.cwi.crescer.melevaai.service.interno.CorridaSituacaoService;
import br.com.cwi.crescer.melevaai.util.CorridaFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IniciarCorridaServiceTest {

    @InjectMocks
    private IniciarCorridaService service;

    @Mock
    private CorridaRepository corridaRepository;

    @Mock
    private TimeService timeService;

    @Mock
    private BuscarCorridaService buscarCorridaService;

    @Mock
    private CorridaSituacaoService corridaSituacaoService;

    @Captor
    private ArgumentCaptor<Corrida> corridaCaptor;

    @Test
    @DisplayName("Deve iniciar corrida e retornar resposta com sucesso")
    void deveIniciarCorridaERetornarRespostaComSucesso() {
        Corrida corrida = CorridaFactory.get();
        LocalDateTime dataMock = LocalDateTime.parse("2022-01-01T10:00:00");

        when(buscarCorridaService.buscarPorId(1L))
                .thenReturn(corrida);
        when(timeService.nowWithTime())
                .thenReturn(dataMock);

        IniciarCorridaResponse resultado = service.iniciar(1L);

        verify(timeService, times(2)).nowWithTime();
        verify(corridaSituacaoService).verificarSolicitada(corridaCaptor.capture());

        Corrida corridaInterna = corridaCaptor.getValue();
        assertEquals(1200L, resultado.getTempoEstimado());
        assertEquals(new BigDecimal("240.00"), resultado.getValorEstimado());
        assertEquals(dataMock, corridaInterna.getHoraInicio());
        assertEquals(dataMock.plusSeconds(1200L), corridaInterna.getHoraFinal());
        assertEquals(SituacaoCorrida.INICIADA, corridaInterna.getSituacaoCorrida());
        assertEquals(SituacaoUsuario.OCUPADO, corridaInterna.getMotorista().getSituacaoUsuario());
        assertEquals(SituacaoUsuario.OCUPADO, corridaInterna.getPassageiro().getSituacaoUsuario());
    }
}