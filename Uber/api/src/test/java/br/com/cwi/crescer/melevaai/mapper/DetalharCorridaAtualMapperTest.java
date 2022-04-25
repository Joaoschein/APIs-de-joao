package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.controller.response.DetalharCorridaResponse;
import br.com.cwi.crescer.melevaai.model.Corrida;
import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.model.SituacaoCorrida;
import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import br.com.cwi.crescer.melevaai.util.CorridaFactory;
import br.com.cwi.crescer.melevaai.util.MotoristaFactory;
import br.com.cwi.crescer.melevaai.util.PassageiroFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DetalharCorridaAtualMapperTest {

    @InjectMocks
    private DetalharCorridaAtualMapper mapper;

    @Mock
    private CorridaRepository corridaRepository;

    @Test
    @DisplayName("Deve transoformar corrida em DetalharCorridaResponse com sucesso ao receber motorista")
    void deveRetornarDetalharCorridaResponseComSucessoAoReceberMotoristaComCorrida() {
        Motorista motorista = MotoristaFactory.get();
        Corrida corrida = CorridaFactory.get();

        when(corridaRepository.findFirstByMotoristaAndSituacaoCorridaNot(motorista, SituacaoCorrida.FINALIZADA))
                .thenReturn(corrida);

        DetalharCorridaResponse resultado = mapper.toResponse(motorista);

        verify(corridaRepository).findFirstByMotoristaAndSituacaoCorridaNot(motorista, SituacaoCorrida.FINALIZADA);

        assertEquals(1L, resultado.getId());
        assertEquals("Nome Teste", resultado.getNomeMotorista());
        assertEquals("Passageiro Teste", resultado.getNomePassageiro());
        assertEquals(10D, resultado.getDistancia());
        assertEquals(SituacaoCorrida.SOLICITADA, resultado.getSituacao());
    }

    @Test
    @DisplayName("Deve retornar nulo ao mapear motorista sem corrida")
    void deveRetornarNuloAoMapearMotoristaSemCorrida(){
        Motorista motorista = MotoristaFactory.get();

        when(corridaRepository.findFirstByMotoristaAndSituacaoCorridaNot(motorista, SituacaoCorrida.FINALIZADA))
                .thenReturn(null);

        DetalharCorridaResponse resultado = mapper.toResponse(motorista);

        verify(corridaRepository).findFirstByMotoristaAndSituacaoCorridaNot(motorista, SituacaoCorrida.FINALIZADA);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Deve transoformar corrida em DetalharCorridaResponse com sucesso ao receber motorista")
    void deveRetornarDetalharCorridaResponseComSucessoAoReceberPassageiroComCorrida() {
        Passageiro passageiro = PassageiroFactory.get();
        Corrida corrida = CorridaFactory.get();

        when(corridaRepository.findFirstByPassageiroAndSituacaoCorridaNot(passageiro, SituacaoCorrida.FINALIZADA))
                .thenReturn(corrida);

        DetalharCorridaResponse resultado = mapper.toResponse(passageiro);

        verify(corridaRepository).findFirstByPassageiroAndSituacaoCorridaNot(passageiro, SituacaoCorrida.FINALIZADA);

        assertEquals(1L, resultado.getId());
        assertEquals("Nome Teste", resultado.getNomeMotorista());
        assertEquals("Passageiro Teste", resultado.getNomePassageiro());
        assertEquals(10D, resultado.getDistancia());
        assertEquals(SituacaoCorrida.SOLICITADA, resultado.getSituacao());
    }

    @Test
    @DisplayName("Deve retornar nulo ao mapear passageiro sem corrida")
    void deveRetornarNuloAoMapearPassageiroSemCorrida(){
        Passageiro passageiro = PassageiroFactory.get();

        when(corridaRepository.findFirstByPassageiroAndSituacaoCorridaNot(passageiro, SituacaoCorrida.FINALIZADA))
                .thenReturn(null);

        DetalharCorridaResponse resultado = mapper.toResponse(passageiro);

        verify(corridaRepository).findFirstByPassageiroAndSituacaoCorridaNot(passageiro, SituacaoCorrida.FINALIZADA);

        assertNull(resultado);
    }
}