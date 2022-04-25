package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.CreditoRequest;
import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import br.com.cwi.crescer.melevaai.service.interno.MotoristaSituacaoService;
import br.com.cwi.crescer.melevaai.util.MotoristaFactory;
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
class SacarCreditoServiceTest {

    @InjectMocks
    private SacarCreditoService service;

    @Mock
    private BuscarMotoristaService buscarMotoristaService;

    @Mock
    private MotoristaRepository motoristaRepository;

    @Mock
    private MotoristaSituacaoService motoristaSituacaoService;

    @Captor
    private ArgumentCaptor<Motorista> motoristaCaptor;

    @Test
    @DisplayName("Deve sacar da conta do motorista com sucesso")
    void deveSacarDaContaDoMotoristaComSucesso() {
        CreditoRequest request = new CreditoRequest();
        request.setValor(new BigDecimal("100"));
        Motorista motorista = MotoristaFactory.get();
        motorista.setCreditos(new BigDecimal("100"));

        when(buscarMotoristaService.buscarPorId(1L))
                .thenReturn(motorista);

        service.sacar(1L, request);

        verify(motoristaSituacaoService).verificarAtivo(motorista);
        verify(buscarMotoristaService).buscarPorId(1L);
        verify(motoristaRepository).save(motoristaCaptor.capture());

        Motorista motoristaInterno = motoristaCaptor.getValue();
        assertEquals(BigDecimal.ZERO,motoristaInterno.getCreditos());
    }

    @Test
    @DisplayName("Deve lancar erro ao tentar sacar sem saldo suficiente")
    void deveLancarErroAoSacarSemSaldoSuficiente() {
        CreditoRequest request = new CreditoRequest();
        request.setValor(new BigDecimal("100"));
        Motorista motorista = MotoristaFactory.get();

        when(buscarMotoristaService.buscarPorId(1L))
                .thenReturn(motorista);

        assertThrows(ResponseStatusException.class, () -> {
            service.sacar(1L, request);
        });

        verify(buscarMotoristaService).buscarPorId(1L);
    }
}