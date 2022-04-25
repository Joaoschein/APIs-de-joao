package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.AvaliacaoRequest;
import br.com.cwi.crescer.melevaai.model.*;
import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import br.com.cwi.crescer.melevaai.repository.PassageiroRepository;
import br.com.cwi.crescer.melevaai.service.interno.MotoristaSituacaoService;
import br.com.cwi.crescer.melevaai.service.interno.PassageiroSituacaoService;
import br.com.cwi.crescer.melevaai.util.CorridaFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AvaliacaoServiceTest {

    @InjectMocks
    private AvaliacaoService service;

    @Mock
    private MotoristaRepository motoristaRepository;

    @Mock
    private PassageiroRepository passageiroRepository;

    @Mock
    private BuscarMotoristaService buscarMotoristaService;

    @Mock
    private BuscarPassageiroService buscarPassageiroService;

    @Mock
    private BuscarCorridaService buscarCorridaService;

    @Mock
    private MotoristaSituacaoService motoristaSituacaoService;

    @Mock
    private PassageiroSituacaoService passageiroSituacaoService;

    @Mock
    private CorridaRepository corridaRepository;

    @Captor
    private ArgumentCaptor<Corrida> corridaCaptor;

    @Captor
    private ArgumentCaptor<Motorista> motoristaCaptor;

    @Captor
    private ArgumentCaptor<Passageiro> passageiroCaptor;

    @Test
    @DisplayName("Deve avaliar motorista e salvar com sucesso")
    void deveAvaliarMotoristaComSucesso() {
        AvaliacaoRequest request = new AvaliacaoRequest();
        request.setNota(5);
        Corrida corrida = CorridaFactory.get();

        when(buscarCorridaService.buscarPorId(1L))
                .thenReturn(corrida);
        when(corridaRepository.getMediaDeAvaliacoesMotorista(corrida.getMotorista()))
                .thenReturn(new BigDecimal("4.5"));

        service.avaliarMotorista(1L, request);

        verify(corridaRepository).save(corridaCaptor.capture());
        verify(motoristaRepository).save(motoristaCaptor.capture());

        Motorista motoristaInterno = motoristaCaptor.getValue();
        verify(corridaRepository).getMediaDeAvaliacoesMotorista(motoristaInterno);
        verify(motoristaRepository).save(motoristaInterno);

        Corrida corridaInterna = corridaCaptor.getValue();
        assertEquals(5, corridaInterna.getAvaliacaoMotorista());
        assertEquals(new BigDecimal("4.5"), motoristaInterno.getAvaliacao());
    }

    @Test
    @DisplayName("Deve avaliar passageiro e salvar com sucesso")
    void deveAvaliarPassageiroComSucesso() {
        AvaliacaoRequest request = new AvaliacaoRequest();
        request.setNota(5);
        Corrida corrida = CorridaFactory.get();

        when(buscarCorridaService.buscarPorId(1L))
                .thenReturn(corrida);
        when(corridaRepository.getMediaDeAvaliacoesPassageiro(corrida.getPassageiro()))
                .thenReturn(new BigDecimal("4.5"));

        service.avaliarPassageiro(1L, request);

        verify(corridaRepository).save(corridaCaptor.capture());
        verify(passageiroRepository).save(passageiroCaptor.capture());

        Passageiro passageiroInterno = passageiroCaptor.getValue();
        verify(corridaRepository).getMediaDeAvaliacoesPassageiro(passageiroInterno);
        verify(passageiroRepository).save(passageiroInterno);

        Corrida corridaInterna = corridaCaptor.getValue();
        assertEquals(5, corridaInterna.getAvaliacaoPassageiro());
        assertEquals(new BigDecimal("4.5"), passageiroInterno.getAvaliacao());
    }
}



