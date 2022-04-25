package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.controller.response.BuscarPassageiroResponse;
import br.com.cwi.crescer.melevaai.controller.response.DetalharCorridaResponse;
import br.com.cwi.crescer.melevaai.model.Passageiro;
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

@ExtendWith(MockitoExtension.class)
class BuscarPassageiroMapperTest {

    @InjectMocks
    private BuscarPassageiroMapper mapper;

    @Mock
    private DetalharCorridaAtualMapper detalharCorridaAtualMapper;

    @Test
    @DisplayName("Deve retornar response com corrida caso passageiro possua corrida em andamento")
    void deveRetornarResponseComCorridaCasoPassageiroPossuaCorridaEmAndamento() {
        Passageiro passageiro = PassageiroFactory.get();

        Mockito.when(detalharCorridaAtualMapper.toResponse(passageiro))
                .thenReturn(new DetalharCorridaResponse());

        BuscarPassageiroResponse resultado = mapper.toResponse(passageiro);

        Mockito.verify(detalharCorridaAtualMapper).toResponse(passageiro);

        Assertions.assertNotNull(resultado.getCorrida());
    }
    @Test
    @DisplayName("Deve retornar response nula caso passageiro não possua corrida em andamento")
    void deveRetornarNuloAoMapearPassageiroSemCorrida() {
        Passageiro passageiro = PassageiroFactory.get();

        Mockito.when(detalharCorridaAtualMapper.toResponse(passageiro))
                .thenReturn(null);

        BuscarPassageiroResponse resultado = mapper.toResponse(passageiro);

        Mockito.verify(detalharCorridaAtualMapper).toResponse(passageiro);

        Assertions.assertNull(resultado.getCorrida());
    }
}