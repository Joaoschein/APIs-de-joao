package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.SolicitarCorridaRequest;
import br.com.cwi.crescer.melevaai.controller.response.SolicitarCorridaResponse;
import br.com.cwi.crescer.melevaai.model.*;
import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import br.com.cwi.crescer.melevaai.service.interno.MotoristaSituacaoService;
import br.com.cwi.crescer.melevaai.service.interno.PassageiroSituacaoService;
import br.com.cwi.crescer.melevaai.util.CorridaFactory;
import br.com.cwi.crescer.melevaai.util.MotoristaFactory;
import br.com.cwi.crescer.melevaai.util.PassageiroFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class SolicitarCorridaServiceTest {

    @InjectMocks
    private SolicitarCorridaService service;

    @Mock
    private BuscarPassageiroService buscarPassageiroService;

    @Mock
    private BuscarMotoristaService buscarMotoristaService;

    @Mock
    private MotoristaRepository motoristaRepository;

    @Mock
    private CorridaRepository corridaRepository;

    @Mock
    private GerarTempoEstimadoService gerarTempoEstimadoService;

    @Mock
    private MotoristaSituacaoService motoristaSituacaoService;

    @Mock
    private PassageiroSituacaoService passageiroSituacaoService;

    @Captor
    private ArgumentCaptor<Corrida> corridaCaptor;

    @Test
    @DisplayName("Deve solicitar corrida e retornar resposta com sucesso")
    void deveSolicitarCorridaERetornarRespostaComSucesso() {
        SolicitarCorridaRequest request = SolicitarCorridaRequest.builder()
                .id(1L)
                .pontoFinalX(5D)
                .pontoFinalY(0D)
                .pontoInicialX(0D)
                .pontoInicialY(0D)
                .build();

        Motorista motorista = MotoristaFactory.get();
        Passageiro passageiro = PassageiroFactory.get();

        Mockito.when(buscarPassageiroService.buscarPorId(1L))
                .thenReturn(passageiro);
        Mockito.when(buscarMotoristaService.buscarLivreComMaiorAvaliacao())
                .thenReturn(motorista);
        Mockito.when(corridaRepository.save(CorridaFactory.getCorridaSemId()))
                .thenReturn(CorridaFactory.get());
        Mockito.when(gerarTempoEstimadoService.gerar())
                        .thenReturn(7);

        SolicitarCorridaResponse resultado = service.solicitar(request);

        verify(buscarPassageiroService).buscarPorId(1L);
        verify(buscarMotoristaService).buscarLivreComMaiorAvaliacao();
        verify(corridaRepository).save(corridaCaptor.capture());
        verify(gerarTempoEstimadoService).gerar();
        verify(passageiroSituacaoService).verificarLivre(passageiro);
        verify(motoristaSituacaoService).verificarLivre(motorista);

        Corrida corridaGeradaInternamente = corridaCaptor.getValue();
        assertEquals(5D, corridaGeradaInternamente.getDistancia());
        assertEquals(1L, resultado.getId());
        assertEquals(motorista.getNome(), resultado.getNomeMotorista());
        assertEquals(motorista.getVeiculo().getPlaca(), resultado.getVeiculo().getPlaca());
        assertEquals(br.com.cwi.crescer.melevaai.model.SituacaoCorrida.SOLICITADA, corridaGeradaInternamente.getSituacaoCorrida());
        assertEquals(SituacaoUsuario.OCUPADO, corridaGeradaInternamente.getMotorista().getSituacaoUsuario());
        assertEquals(SituacaoUsuario.OCUPADO, corridaGeradaInternamente.getPassageiro().getSituacaoUsuario());
    }
}