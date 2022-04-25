package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.mapper.BuscarMotoristaMapper;
import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.util.MotoristaFactory;
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
class GetMotoristaServiceTest {

    @InjectMocks
    private GetMotoristaService service;

    @Mock
    private BuscarMotoristaService buscarMotoristaService;

    @Mock
    private BuscarMotoristaMapper buscarMotoristaMapper;

    @Test
    @DisplayName("Deve retornar response de motorista com sucesso")
    void deveRetornarResponseComSucesso() {
        Motorista motorista = MotoristaFactory.get();

        when(buscarMotoristaService.buscarPorId(1L))
                .thenReturn(motorista);

        service.buscar(1L);

        verify(buscarMotoristaService).buscarPorId(1L);
        verify(buscarMotoristaMapper).toResponse(motorista);
    }
}