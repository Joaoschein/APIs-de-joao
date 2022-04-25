package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.mapper.BuscarPassageiroMapper;
import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.util.MotoristaFactory;
import br.com.cwi.crescer.melevaai.util.PassageiroFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPassageiroServiceTest {

    @InjectMocks
    private GetPassageiroService service;

    @Mock
    private BuscarPassageiroService buscarPassageiroService;

    @Mock
    private BuscarPassageiroMapper buscarPassageiroMapper;

    @Test
    @DisplayName("Deve retornar response de passageiro com sucesso")
    void deveRetornarResponseComSucesso() {
        Passageiro passageiro = PassageiroFactory.get();

        when(buscarPassageiroService.buscarPorId(1L))
                .thenReturn(passageiro);

        service.buscar(1L);

        verify(buscarPassageiroService).buscarPorId(1L);
        verify(buscarPassageiroMapper).toResponse(passageiro);
    }
}