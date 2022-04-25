package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.controller.response.BuscarMotoristaResponse;
import br.com.cwi.crescer.melevaai.controller.response.BuscarPassageiroResponse;
import br.com.cwi.crescer.melevaai.controller.response.DetalharCorridaResponse;
import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.Passageiro;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarMotoristaMapperTest {

    @InjectMocks
    private BuscarMotoristaMapper mapper;

    @Mock
    private DetalharCorridaAtualMapper detalharCorridaAtualMapper;

    @Test
    @DisplayName("Deve retornar response com corrida caso motorista possua corrida em andamento")
    void deveRetornarResponseComCorridaCasoPassageiroPossuaCorridaEmAndamento() {
        Motorista motorista = MotoristaFactory.get();

        when(detalharCorridaAtualMapper.toResponse(motorista))
                .thenReturn(new DetalharCorridaResponse());

        BuscarMotoristaResponse resultado = mapper.toResponse(motorista);

        verify(detalharCorridaAtualMapper).toResponse(motorista);

        Assertions.assertNotNull(resultado.getCorrida());
    }
    @Test
    @DisplayName("Deve retornar response nula caso motorista não possua corrida em andamento")
    void deveRetornarNuloAoMapearPassageiroSemCorrida() {
        Motorista motorista = MotoristaFactory.get();

        when(detalharCorridaAtualMapper.toResponse(motorista))
                .thenReturn(null);

        BuscarMotoristaResponse resultado = mapper.toResponse(motorista);

        verify(detalharCorridaAtualMapper).toResponse(motorista);

        assertNull(resultado.getCorrida());
    }
}